# Dicee
Dice rolling application made using Kotlin and Android studio with support for up to 99 players and 6 dice, when launching the application you are greeted by a setup screen where the input is validated and the locale can be selected
<p float="left">
  <img src="https://github.com/filipopo/Pare/assets/4060824/0915f420-3088-4f7c-a1eb-a986872609f7" width="250" alt="setup screen">
  <img src="https://github.com/filipopo/Pare/assets/4060824/c20257ce-3645-496b-80bb-f929279dcf46" width="250" alt="setup screen validation">
</p>
The main activity after completing the setup is a list of players with their current roll and total score, players can roll in sequence one by one or use the roll for all button which will roll for all players which haven't already rolled in that round, the list of players is scrollable
<p float="left">
  <img src="https://github.com/filipopo/Pare/assets/4060824/50b0254d-ae6f-46a0-a0ff-96c4d61c0af8" width="250" alt="main screen">
  <img src="https://github.com/filipopo/Pare/assets/4060824/4a956a64-ebae-4f0f-9470-71ed99dfc9ae" width="250" alt="main screen roll scrolled">
</p>
Clicking on the reset button ends the game and finds out who the winner is, prompting the user to continue playing with the same settings or return back to the setup screen
<p float="left">
  <img src="https://github.com/filipopo/Pare/assets/4060824/31c601c3-b249-4634-893d-2c52439ac99c" width="500" alt="winner screen">
</p>
The application also works in landscape mode and tells when the winning score is a tie (by which players)
<p float="left">
  <img src="https://github.com/filipopo/Pare/assets/4060824/9c727d3a-fe24-43c1-9e6d-ced3371fa4ac" width="500" alt="main screen landscape"><br>
  <img src="https://github.com/filipopo/Pare/assets/4060824/e9752fe6-cf70-4b3e-a5c2-aa29b244db7f" width="500" alt="main screen landscape roll scrolled">
</p>
The game can be paused at any time by using the back button, when the game is paused or won the roll buttons don't work
<p float="left">
  <img src="https://github.com/filipopo/Pare/assets/4060824/19f05df9-d0d5-4f31-a1b6-d9c93259f49c" width="500" alt="main screen paused roll">
</p>
