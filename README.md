Mp3-Decoder-Encoder
===================

  This is about encoding or maybe better to say encrypting .mp3 files . the Mp3Header class gets info about Mp3
  file then you can use it in this class . first you find the headers then
  in data section there is a method called Encode which here is a very simple
  one . you can replace with whatever you want . and also there is another one
  which is called Decode that do the reverse thing .the point of this project is 
  that the encoded file can also be played in media players because header
  doesnt change.it works perfectly but some mp3 files that have dirty metadata
  tags cause it take a little long for encoding . what you need to do is just
  skip the metadata . which very simple .  In Mp3Header class you can find most
  of the info about mp3 file you selected , if you need them.
