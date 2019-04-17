# Spell-A-Rhyme
## Installation Guide
### Prerequisites
- Download [Android Studio](https://developer.android.com/studio/index.html)
### Download Instructions
- Open Android Studio
- Press ‘Check out project from Version Control’
- Press ‘GitHub’
- Enter ‘https://github.com/adussa3/Spell-A-Rhyme.git’
- Clone
- You can alternatively clone or download the project from the GitHub link, and import the project into Android Studio
- Press 'Make Project'
### Running the Application on an Emulator
- Open Android Studio
- Tools > AVD Manager
- Press '+ Create Virtual Device...'
- Choose any device (we used Nexus 4)
- Choose Nougat API 24
- Run > Run 'app'
- Choose the device you just created and click OK
### Troubleshooting
- When the files or ID names are in the project, but not recognized, Build > Clean Project and Build > Rebuild Project

<p align="center">
  <img src="https://github.com/adussa3/Spell-A-Rhyme/blob/update_readme/images/Build.png" width="70%">
</p>

- When trying to run the application on an emulator, if there is an INSTALL_FAILED_INSUFFICIENT_STORAGE error, first try clearing the data in the emulator by going to Settings > Storage > Internal shared storage > Apps > Spell A Rhyme > Clear Data > OK. 

- Another way to resolve this issue is to open the AVD Manager. Under the "Actions" column, click on the arrow of the device and select "Wipe Data"

<p align="center">
  <img src="https://github.com/adussa3/Spell-A-Rhyme/blob/update_readme/images/Wipe_Data.png" width="70%">
</p>

- If this doesn’t work, delete the emulator and recreate it.
## Release Notes
### Software Features
- **Unit 1** is a tutorial level that teaches the player how to spell words in the application. There are three words (“cat”, “dog”, “hen”) that the player can practice on by clicking on the word’s corresponding image. Once the word’s image occupies the majority of the screen, the player can tap on either the blank or the image segment above the blank to hear the phoneme to spell. The player can also tap on the letter options on the left to hear the letter’s phoneme. If the player selects the correct letter given the currently selected blank, the letter option will disappear and occupy the blank, and a “ding” sound will play. Then the corresponding image segment changes from black and white to colored. If the incorrect letter is selected, a “bloop” sound will play and the letter will not occupy the blank.

<p align="center">
  <img src="https://github.com/adussa3/Spell-A-Rhyme/blob/update_readme/images/Unit1.png" width="65%">
</p>

- **Unit 2** is similar to Unit 1, but the player spells three words at a time as opposed to one at a time in Unit 1. Otherwise, the way to occupy the blanks remains the same. The player must spell the set of 3 words correctly three times in a row in order for the words to be “learned.” This means that after all the words are spelled correctly (less than 3 times though), the images reset from colored back to black and white until after the third correct spell. Learned words will show up colored in the bank (described later).

<p align="center">
  <img src="https://github.com/adussa3/Spell-A-Rhyme/blob/update_readme/images/Unit2.png" width="65%">
</p>

- **Unit 3** focuses on spelling words with vowels. The player is first presented with vowels to choose from. Once a vowel is chosen (e.g. “A”), the screen shows images of words containing “A” that the player can choose to spell. Once the player chooses a word to spell, the image shows up with letter blanks below it. Initially, all blanks are filled except one, which is the blank that holds the vowel that was selected earlier. Once the player correctly fills this blank, another blank is randomly removed. This is repeated until the player has to occupy all blanks. Once the player correctly spells all blanks, the word is learned. Some words have a silent letter that will always remain occupied and are colored a different (e.g. “e” in “ape”).

<p align="center">
  <img src="https://github.com/adussa3/Spell-A-Rhyme/blob/update_readme/images/Unit3.png" width="65%">
</p>

- **Unit 4** focuses on spelling words with consonants. This is exactly the same as Unit 3 describe above, except the first letter removed is a consonant.

<p align="center">
  <img src="https://github.com/adussa3/Spell-A-Rhyme/blob/update_readme/images/Unit4.png" width="65%">
</p>

- **Bank** stores the words learned by the player. The bank is organized by category (e.g. animals, people, toys, etc.). After clicking on a category, images of all the words of that category are shown, but only the learned words are colored. The words that are not learned (i.e. have not been spelled correctly 3 times in a row) remain black and white.
<p align="center">
  <img src="https://github.com/adussa3/Spell-A-Rhyme/blob/update_readme/images/Bank.png" width="45%">
  <img src="https://github.com/adussa3/Spell-A-Rhyme/blob/update_readme/images/Animal_Bank.png" width="45%">
</p>
- Throughout the application, there are scrolls that can be scrolled through regularly, or with left and right arrows (tapping is easier for younger players).
- The font is updated to be lowercase Comic Neue.
- Images were all standardized to be the same canvas size so that they would show up equally spaced in the application.
- The blanks were updated so that selected blank now blinks in orange so the player more easily recognizes which blank is selected.

### Bug Fixes
- Crash caused by vertical orientation fixed by restricting application to horizontal view
- Bank was not updating correctly because the category of each word was not properly provided
- During Unit 2 gameplay, after spelling all three words correctly, the first two segments of 'hen' remained uncolored
- On the Unit 4 consonant selection screen, consonants were misplaced because of a missing consonant letter
- On Unit 3 and Unit 4 selection screens, the image buttons were different sizes because of significant differences in source images' sizes

### Known Bugs and Defects
- **Bug**: Depending on the API that’s installed, some features are lost in the application
- **Bug**: in Units 3 and 4, in a word where there are repeated letters (e.g. bubble) you can click on “b” twice in the same blank and there will not be another “b” option for the second “b” blank
- **Bug**: in Units 3 and 4, after a while of playing, recreateActivity() does not finish() the current activity. This means that the user will have to click the exit door multiple times to exit out of spelling the word.
- **Bug**: Scrolling with arrow buttons in Unit3WordListActivity and Unit4WordListActivity has a tendency to speed up dramatically while held down.
- **Missing**: Attach the unspelled words in the Bank to the gameplay to learn it
- **Missing**: Unit 2 has missing images for words. Currently, only two three-word sets are able to be selected. The other images are vertical and difficult to integrate into a level originally designed for just horizontal segmented images.
- **Note:** after the Welcome screen, there are buttons for Join a Class, I’m a Teacher, and within the Free Play there is a Who’s Playing page (only Bob works for now). These additional features are to be implemented in the future and were not part of our backlog.

