
name := "Connect4"

version := "0.1"

scalaVersion := "2.12.8"

resolvers += "jitpack" at "https://jitpack.io"

libraryDependencies += "org.typelevel"                 %% "cats-effect"            % "1.3.1"
libraryDependencies += "org.scalatest"                 %% "scalatest"              % "3.0.0"  % Test
