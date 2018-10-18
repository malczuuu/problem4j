# How to setup with Spring

1.  Include dependencies.

    Add [JitPack](https://jitpack.io/) repository.

    ```groovy
    repositories {
        maven { url "https://jitpack.io/" }
    }
    ```

    Add `problem4j` package.

    ```groovy
    dependencies {
        implementation "com.github.malczuuu.problem4j:problem4j-spring:{version}"
    }
    ```

    This package depends on `problem4j-core` and `problem4j-jackson` in appropriate versions, so no need to include them implicitly.

2.  Override `ProblemResponseEntityExceptionHandler` with `@ControllerAdvice` annotation.

    ```java
    import org.springframework.web.bind.annotation.ControllerAdvice;
    import pl.malczuuu.problem4j.spring.DefaultProblemSupplier;
    import pl.malczuuu.problem4j.spring.ProblemResponseEntityExceptionHandler;

    @ControllerAdvice
    public class ApplicationResponseEntityExceptionHandler
        extends ProblemResponseEntityExceptionHandler {

      public ApplicationResponseEntityExceptionHandler() {
        super(new DefaultProblemSupplier());
      }
    }
    ```

    You can change default responses on predefined exception by overriding `AbstractProblemSupplier` and passing it in base constructor.

    Handle any other exception by declaring `@ExceptionHandler` method.

    ```java
    import java.net.URI;
    import org.springframework.http.HttpHeaders;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.ExceptionHandler;
    import org.springframework.web.context.request.WebRequest;
    import pl.malczuuu.problem4j.core.Problem;
    import pl.malczuuu.problem4j.spring.ProblemResponseEntityExceptionHandler;

    public class ApplicationResponseEntityExceptionHandler
        extends ProblemResponseEntityExceptionHandler {

    // ...

      @ExceptionHandler({NullPointerException.class})
      public ResponseEntity<Object> handleProblemException(
          NullPointerException ex, WebRequest request) {
        Problem problem = 
            Problem.builder()
                .type(URI.create("https://www.mydomain.org/error-types/null-pointer-exception"))
                .title("Internal server error")
                .status(500)
                .detail(
                    "We're sorry, some developer have forgotten about handling NullPointerException");
        HttpStatus status = HttpStatus.valueOf(problem.getStatus());
        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
      }
    }
    ````

3.  Configure Jackson module.

    ```java
    import com.fasterxml.jackson.databind.Module;
    import org.springframework.context.annotation.Bean;
    import org.springframework.context.annotation.Configuration;
    import pl.malczuuu.problem4j.jackson.ProblemModuleFactory;

    @Configuration
    public class JacksonConfiguration {

      @Bean
      public Module problemModule() {
        return new ProblemModuleFactory().createProblemModule();
      }
    }
    ```

4.  Throw `ProblemException` or it's subclasses from anywhere in your REST methods. All errors will be automatically handled and status will be returned to user.