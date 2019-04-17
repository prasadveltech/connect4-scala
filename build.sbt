
name := "Connect4"

version := "0.1"

scalaVersion := "2.12.8"

resolvers += "jitpack" at "https://jitpack.io"

libraryDependencies += "com.github.westernsam"         % "replrunner"              % "v0.0.1" % Test
libraryDependencies += "org.scalatest"                 %% "scalatest"              % "3.0.0"  % Test
