# Chat Room Server

A chat room application based on command line interface.

## Java API

Here I list some java API that you may need to use for this `chat room server` project.

- java.io.InputStream
- java.io.OutputStream
- java.net.Socket

## Usage

1. Compile and run the main java program.

    ```bash
    javac Main.java
    java Main
    ```

2. After using `java Main` to execute the server code, the chat server will be started. At this time, open `n` terminals (CLI) and using following command for creating `n clients`.

    ```bash
    nc localhost 'The port number you specify'
    ```

3. Now, the prompt you type should be able to echo back to `(n - 1) clients` other than the current client.
