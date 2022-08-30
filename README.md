# Typing Game

![Screenshot 2022-08-29 174412](https://user-images.githubusercontent.com/92118801/187323309-6e91aab4-7d8a-456a-b5a1-5a88b208580c.png)
![Screenshot 2022-08-29 174048](https://user-images.githubusercontent.com/92118801/187323148-77cd92ce-eb37-4718-a1d9-62f98927d68a.png)

![Screenshot 2022-08-29 174100](https://user-images.githubusercontent.com/92118801/187323150-8e2f994c-4c1c-4ad1-84b5-3434b5b4d8df.png)
![Screenshot 2022-08-29 174124](https://user-images.githubusercontent.com/92118801/187323214-3d3a7afe-c724-4546-90c9-1b2310801945.png)

![Screenshot 2022-08-29 174140](https://user-images.githubusercontent.com/92118801/187323320-228b1d47-6d0d-4832-8af3-f879f987613e.png)

### dare to test your typing abilities...

test

This game will allow its user to practice their typing skills by typing
according to what is on their screen. The application will track may track
typing accuracy, words per minute, and even possibly have a scoring system. This game
is made for people of all ages as typing is an essential skill that allows for fluent
technology usage. Therefore, kids who are just learning how to type, or adults who are trying
to improve their typing can use this game to have fun while developing a skill!

### Inspiration

This project interests me because I began learning how to type when I was in grade 2, and
the websites that I practiced on such as NitroType were extremely addictive. I would like
learn how to code a simple game that can benefit its player in multiple ways. 

### User Stories

#### Phase 1

As a user, I want to be able to attain a score and add it to a scoreboard. 

As a user, I want to be able to view all my previous scores and set new high scores on the score board.

As a user, I want to be able to see the words that I need to type and the output of what I am currently typing. 

As a user, I want to be able to see my percentage accuracy. 

#### Phase 2

As a user, I want to be able to save my player information so that I can see if I can get a higher score the 
next time I play whenever I quit the game. It will automatically save for me. 

As a user, I want to be able to load into the same player account the next time I play.

#### Phase 3

As a user, I want to be able to add my scores to a scoreboard, see a countdown, see the words I am typing, and the 
ords I need to type.  

As a user, I want to be able to login to my account and prompted to save my user information before I quit.

#### Phase 4

Task 2: 

Allan scored 0.0 with an accuracy of 0.0% at Wed Mar 30 10:38:06 PDT 2022
Allan scored 6000.0 with an accuracy of 6.0% at Wed Mar 30 10:38:10 PDT 2022
Allan scored 6000.0 with an accuracy of 6.0% at Wed Mar 30 10:38:13 PDT 2022

#### Reflection and Further Development

1. After drawing the UML diagram, I realized how much coupling there is in my project. Although I feel like there is high
cohesion within the code, all the classes depend on each other, which is poor coding style as if one class breaks, 
everything else breaks. Specifically, I realized that I implemented bi-directional relationships just to pass on the same 
data such as player information to the next class. However, this could have been fixed by using the singleton design pattern
as I could have made the initial screens (loadInScreen and SignUpScreen) accessible to all other classes. Or I could
have implemented a more structured hierarchy so that I could just access the related classes data and so on.
2. For many panels, I did not use a layout because I wanted to freely set the location of my objects. However, this
just means that if I wanted to redesign this in the future, I would have to redo all the layout planning of the panels 
and change all its variables because I did not use the single point of responsibility principle for dimensions such as 
width and height.
3. There is a some common behaviour between my classes such as setting up the bi-drectional relationships. I 
also reuse a lot of code within classes as well, so I could have created helper methods to reduce code clones. 
4. I could have implemented other functions to the game such as a timer countdown, colour matching to words typed 
correctly, or even a password for users. I could have also fixed the bug about not being able to type before the countdown
if I knew more about multi-threading and synchronous programming.

