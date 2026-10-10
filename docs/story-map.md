# Story Map

Read left to right as the player's journey. Each horizontal band is a vertical
slice: the thinnest end-to-end path a player can actually walk. The top slice is
the MVP.

## Slice 1 — MVP

### I am a new player
I want to play Discworld MUD, which I have never played
I open the app
I see a list of servers
I select Discworld MUD in the list
The app connects and I see the initial prompt to create a character
I also see a text input field and a "Send" button
When I type in the name of my character and press "Send"
Then I see the game has started

### I want to play
I have a character and I am connected to a server
I see the game world as text, arriving as it happens
At the bottom there is a text input field and a "Send" button
When I type a command (e.g. "look") and press "Send"
Then the server receives it and I see the response
The output scrolls, with the newest text at the bottom

## Later slices

### I want to continue my game
I already have a character and I would like to continue my game
I open the app
I see my saved character
I tap the character; the game reconnects and logs me in

> Deferred: depends on remembering the server and credentials (persistence),
> which is not part of the MVP.
