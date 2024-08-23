# BorcServis Uygulaması
-------------------------

Bu uygulama var olan borçların getirilmesi ve ödeme işlemi için hazırlanmasını sağlar.

# Uygulama Özellikleri
--------------------------

<pre>
Spring Boot : 3.3.2
Java : 17
</pre>

# Environment Variables
---------------------------

<pre>
DB_URL=jdbc:postgresql://localhost:5432/PROJE;
DB_USER=postgres;
DB_PASS=123;
DB_SCHEMA=gsths;
PORT=7000;

SWAGGER_TITLE=Borc Service;
SWAGGER_VERSION=1.0;
SWAGGER_DSC=Borc service ile ilgili islemleri yapan servistir;
SWAGGER_TOS=https://gib.gov.tr;
SWAGGER_NAME=Melih Atalay;
SWAGGER_EMAIL=melih.atalay@gelirler.gov.tr;

RABBITMQ_HOST=localhost;
RABBITMQ_PORT=5672;
RABBITMQ_USER=guest;
RABBITMQ_PASS=guest;
RABBITMQ_EXCHANGE=tahsilat.exchange;
RABBITMQ_ROUTING=odemerk.*;
RBT_QUEUE=odemequeue;
CONSUMER_COUNT=3;
RBT_RETRY_ENABLED=false;
RBT_PREFETCH=1;
</pre>