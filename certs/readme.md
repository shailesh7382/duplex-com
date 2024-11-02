# Configuring Mutual TLS (mTLS) Authentication for gRPC Client and Server

## Overview
This guide explains how to configure mutual TLS (mTLS) authentication for both the gRPC client and server in your project. mTLS ensures that both the client and server authenticate each other, providing a secure communication channel.
Implemented mutual TLS (mTLS) in this project ensures that the communication between the gRPC client and server is encrypted. TLS (Transport Layer Security) is a cryptographic protocol designed to provide secure communication over a computer network. When you configure mTLS, both the client and server authenticate each other using certificates, and the data transmitted between them is encrypted.

Encryption Algorithm Used
The encryption algorithm used in TLS depends on the cipher suite negotiated during the TLS handshake. Common encryption algorithms used in TLS include:

AES (Advanced Encryption Standard): AES is a symmetric encryption algorithm widely used in TLS. It can operate in different modes such as GCM (Galois/Counter Mode) and CBC (Cipher Block Chaining).

Cipher suite used in TLS might look like this: TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256
This cipher suite indicates:

Key Exchange Algorithm: ECDHE (Elliptic Curve Diffie-Hellman Ephemeral)
Authentication Algorithm: RSA
Encryption Algorithm: AES with 128-bit key in GCM mode
Message Authentication Code (MAC): SHA256


## Steps to Configure mTLS

### Step 1: Generate Certificates

You need to generate the server and client certificates along with their private keys and a CA certificate. You can use OpenSSL to generate these certificates.

#### Generate CA Certificate

```sh
openssl genpkey -algorithm RSA -out ca.key
openssl req -x509 -new -nodes -key ca.key -sha256 -days 365 -out ca.crt -subj "/CN=My CA"

Generate Server Certs:

openssl genpkey -algorithm RSA -out server.key
openssl req -new -key server.key -out server.csr -subj "/CN=localhost"
openssl x509 -req -in server.csr -CA ca.crt -CAkey ca.key -CAcreateserial -out server.crt -days 365 -sha256


Generate Client Certs:
openssl genpkey -algorithm RSA -out client.key
openssl req -new -key client.key -out client.csr -subj "/CN=client"
openssl x509 -req -in client.csr -CA ca.crt -CAkey ca.key -CAcreateserial -out client.crt -days 365 -sha256

update application.properties
# Server properties
grpc.server.port=9090
grpc.server.certChainFilePath=path/to/server.crt
grpc.server.privateKeyFilePath=path/to/server.key
grpc.server.trustCertCollectionFilePath=path/to/ca.crt

# Client properties
grpc.client.host=localhost
grpc.client.port=9090
grpc.client.certChainFilePath=path/to/client.crt
grpc.client.privateKeyFilePath=path/to/client.key
grpc.client.trustCertCollectionFilePath=path/to/ca.crt


Besides mutual TLS (mTLS), there are several other ways to enhance the security of the transport layer in a gRPC application. Here are some additional methods to make the transport more secure:

1. Token-Based Authentication
Use token-based authentication mechanisms such as OAuth2, JWT (JSON Web Tokens), or API keys to authenticate and authorize clients.

Example: Using JWT
Client Side: Attach the JWT token to the gRPC request metadata.
Server Side: Validate the JWT token in an interceptor.

2. IP Whitelisting
Restrict access to the gRPC server by allowing only specific IP addresses. Can do a IP Whitelisting Interceptor

3. Data Encryption
Ensure that sensitive data is encrypted before being sent over the network. This can be done at the application level in addition to using TLS.