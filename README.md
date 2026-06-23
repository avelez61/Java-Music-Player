# Java-Music-Player
Simple practice project implementing a command line music player in Java.

## How to Use
Download the .jar file from the releases and place the file in a directory with a .wav file that you want to play. Then run the following command to start the program:
`java -jar MusicPlayer.jar`

The CLI can run the following commands:

ADD SONG - Prompts the user to enter a song title, artist, and file path for the .wav file

REMOVE SONG - Prompts the user to enter a song index number which will remove the song at that index from the playlist

SKIP - The player skips to the next song in the playlist

PREVIOUS - The player goes back to the previous song in the playlist

PLAY - Plays the current song

PAUSE - Pauses the current song

RESTART - Restarts the current song

LIST - Lists all the songs in the current playlist

EXIT - Exits the program

HELP - Prints the list of commands

## Implementation
The player uses `javax.sound.sampled` to load and play .wav files. The `musicplayer` package contains 3 different classes that allow the program to run. The `Song` class is a simple data class that holds information about the songs defined by the user such as the title, artist, and file path to the .wav file. The class does not keep an instance of `File` as that is handled by the `MusicPlayer` class. The `PlayList` class acts as a wrapper class around `ArrayList` which allows the implementation of `PlayList` to change if necessary. A natural data structure that may fit a play list would be a linked list rather than an `ArrayList`. Lastly there is `MusicPlayer` which handles most of the logic between loading songs, playing songs, and calling `PlayList` functions to modify the order of songs in the play list. 

## Limitations
The `javax.sound.sampled` package only works with .wav files and will not work with other file formats. Due to some time constraints looping a current song, skipping forward, and skipping backwards in the song are not currently supported.
