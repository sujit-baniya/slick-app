name := "test"
organization := "com.test"
version := "2017-03-21-110810.8368309 "
scalaVersion := "2.12.17"
scalacOptions ++= Seq("-deprecation", "-unchecked", "-feature")

libraryDependencies ++= Seq(
    "org.http4s" %% "http4s-blaze-server" % "0.16.6",
    "org.http4s" %% "http4s-core"        % "0.16.6",
    "org.http4s" %% "http4s-scala-xml"   % "0.16.6",
    "org.http4s" %% "http4s-argonaut"    % "0.16.6",
    "org.http4s" %% "http4s-dsl"         % "0.16.6",
    "org.http4s" %% "http4s-twirl"       % "0.16.6",
    "org.http4s" %% "http4s-servlet"     % "0.16.6",
    "org.http4s" %% "http4s-tomcat"      % "0.16.6" % "provided",
    "javax.servlet" % "javax.servlet-api" % "3.0.1" % "provided",
    
    "com.typesafe.slick" %% "slick" % "2.1.0",
    "mysql" % "mysql-connector-java" % "8.0.32",
    "org.flywaydb" % "flyway-core" % "4.2.0",

    "commons-codec" % "commons-codec" % "1.10",
    "com.google.guava" % "guava" % "19.0",
    "joda-time" % "joda-time" % "2.9.2",
    "org.joda" % "joda-convert" % "1.8.1", // Fixes compile warnings
    "com.google.code.findbugs" % "jsr305" % "3.0.1",
    "org.clapper" % "grizzled-slf4j_2.11" % "1.0.2",
    "ch.qos.logback" % "logback-classic" % "1.1.6"
)
Compile / scalacOptions += "-Xlint"
Compile / console / scalacOptions --= Seq("-Ywarn-unused", "-Ywarn-unused-import")
Test / scalacOptions += "-Yrangepos"
// ------------------ Deployment / Container ------------------

run / fork := true
