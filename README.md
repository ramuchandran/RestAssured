**1. REST Assured vs Apache HttpClient and RestTemplate**

REST Assured is a high level Java DSL for simplified testing of REST based services built over HTTP. On the other hand, HttpClient is a low level client for simplifying Http Communication, In-fact HttpClient is used by REST Assured under the hood for Http communication.
REST Assured and HttpClient are designed to solve different set of problems.

What does HttpClient do?
HttpClient sends request to and get response from server over HTTP protocol, and also it takes care of the following stuff:

HTTP protocol interception

Secure HTTP connections - SSL/TLS

HTTP proxy server handling

Handles HTTP cookies

Connection pooling for different hosts, keep alive strategy,

multi-threaded request execution

REST Assured
REST Assured is a Java DSL for simplifying testing of REST based services and is built on top of HttpClient. It offers the following additional capabilities:

Validating REST API response using inbuilt Hemcrest Matchers

JSON & XML serialization and deserialization

Extracting JSON data using JsonPath and XML data using XmlPath

Verifying response body, cookies, headers, content-type and http status

Authentication using Basic Auth, Digest Auth, Form Authentication (CSRF support), OAuth (OAuth1 and OAuth2)

verifying multi-part form data

Request and response logging for easy troubleshooting

Session Filters

Spring Mock Mvc Module

Spring Web Test Client Module

Kotlin support

RestTemplate
RestTemplate is also a high level REST client which uses HttpClient under the hood, but it is mostly used in development rather than testing. It lacks most of the testing related features readily available in REST Assured like - Assertion capabilities - inbuilt Hemcrest matchers support, ease of use from testing perspective, out of the box support for various authentication protocols, ease of logging requests response, measuring request time, etc.

Verdict
If you are planning to do functional testing for your REST Endpoints, REST Assured might be the better choice than using HttpClient or RestTemplate.

