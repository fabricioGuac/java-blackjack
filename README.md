# Java-blackjack

## Description 

This project is a Blackjack game designed to help players gain a better understanding of the Hi-Lo card counting system. It displays each card's Hi-Lo value, the running count, the true count, and the remaining decks. However, the true count may be limited in its effectiveness due to the game being played with only the player and the dealer, which can result in a low running count that doesn't significantly impact gameplay.

## Instalation

To install this Blackjack game, clone the GitHub repository, ensure you have Java installed, compile the project by navigating to the project directory and running `javac src/*.java`, then run it with `java -cp src BlackjackMain`.

## Usage

After starting the app, you will be prompted to place a bet between 0 and 100, after which the game begins with the player receiving two cards, each displaying their Hi-Lo value. The dealer will show one card while keeping the other hidden. On the right, you'll see the running count, remaining decks, and true count, and at the bottom, you can choose to hit or stay. Your bet and wallet balance are shown to the left of the buttons. The goal is to get as close to 21 as possible without exceeding it, while having a higher total than the dealer. Face cards are worth 10, and Aces can be worth 1 or 11. If you go over 21, you lose. If you stay, the dealer will draw cards until reaching at least 17. Whoever has the higher hand without busting (going over 21) wins. If you run out of money, your wallet resets to 100, and if the deck runs out, it is automatically refilled.

## Screenshot displaying the deployed app

![Screenshot](/blackjack.png)


## Credits

This project is based on the [Blackjack Java](https://github.com/ImKennyYip/blackjack-java) code by [ImKennyYip](https://github.com/ImKennyYip). I have modified and built upon the original code to suit the needs of my project.


## License

MIT License

Copyright (c) 2024 fabricioGuac

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.