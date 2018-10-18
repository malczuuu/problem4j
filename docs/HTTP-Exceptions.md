# HTTP Exceptions

HTTP exceptions are located in `pl.malczuuu.problem4j.core` package.

| Code  | Reason phrase                        | Exception class                            |
|-------|--------------------------------------|--------------------------------------------|
| `400` | `Bad Request`                        | `BadRequestException`                      |
| `401` | `Unauthorized`                       | `UnauthorizedException`                    |
| `402` | `Payment Required`                   | `PaymentRequiredException`                 |
| `403` | `Forbidden`                          | `ForbiddenException`                       |
| `404` | `Not Found`                          | `NotFoundException`                        |
| `405` | `Method Not Allowed`                 | `MethodNotAllowedException`                |
| `408` | `Request Timeout`                    | `RequestTimeoutException`                  |
| `409` | `Conflict`                           | `ConflictException`                        |
| `410` | `Gone`                               | `GoneException`                            |
| `412` | `Precondition Failed`                | `PreconditionFailedException`              |
| `415` | `Unsupported Media Type`             | `UnsupportedMediaTypeException`            |
| `418` | `I'm a Teapot`                       | `ImATeapotException`                       |
| `422` | `Unprocessable Entity`               | `UnprocessableEntityException`             |
| `451` | `Unavailable For Legal Reasons`      | `UnavailableForLegalReasonsException`      |
| `500` | `Internal Server Error`              | `InternalServerErrorException`             |
| `501` | `Not Implemented`                    | `NotImplementedException`                  |
| `502` | `Bad Gateway`                        | `BadGatewayException`                      |
| `503` | `Service Unavailable`                | `ServiceUnavailableException`              |
| `504` | `Gateway Timeout`                    | `GatewayTimeoutException`                  |
| `507` | `Insufficient Storage`               | `InsufficientStorageException`             |
