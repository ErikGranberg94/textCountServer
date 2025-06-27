# Compilation
This was implemented using `Java 24`, and can be compiled using `Maven`.

# How to run
This implementation requires the following arguments: `filepath` and `port number`.

# Implementation
Due to the simplicity of the implementation I never saw any point in breaking it down into smaller classes. This approach should of course not be taken with a larger, more complex codebase.

The textfile specified by the `filepath` is only accessed while there is a client connected to the server. My reasoning for implementing it this way is that it potentially frees up resources when they are not in use, and it allows for changes to be made to the text file without restarting the server. 

As a consequence: should the file be removed, renamed, or if it did not exist to begin with, the implementation won't notice until after a client has connected. Should this happen, the server will simply close the connection to the client (something it normaly does once it has sent the whole file to the client) and print an error message to the terminal. I decided not to have the server crash if it fails to read the file, to make it potentially be possible to fix the issue without restarting the server.

The server sends the textfile to the client, one line at a time, while keeping none of it in memory.
