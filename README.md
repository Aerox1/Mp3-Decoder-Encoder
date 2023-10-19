Mp3-Decoder-Encoder
===================

This project pertains to the encoding, or perhaps more accurately, the encryption of .mp3 files. The Mp3Header class retrieves information about an Mp3 file, which can then be utilized within this class. Initially, you identify the headers, following which, within the data section, there is a method named Encode. The Encode method provided here is quite simplistic; however, you can replace it with any method of your choice. Additionally, there's a method named Decode that performs the inverse operation.

The objective of this project is to ensure that the encoded file remains playable in media players since the header does not change. This approach works flawlessly, except in cases where the Mp3 files contain corrupt or "dirty" metadata tags, which can extend the encoding process. To overcome this, you merely need to skip over the metadata, a task which is straightforward.

Within the Mp3Header class, you can access most of the information concerning the selected Mp3 file, should you require it.
