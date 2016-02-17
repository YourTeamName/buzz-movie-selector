Introduction
This is our android application semester. We will be building a basic mobile application that serves as a movie information and referral site for Georgia Tech students. To support the site, we will access a set of REST services from Rotten Tomatoes external link: http://developer.rottentomatoes.com/docs.

Projects are usually somewhat open-ended. If we do not specify how you have to do something, then you are free to use your own imagination. I have put some information in the description, but you may design and organize your application as you wish, as long as it meets the basic requirements.

A few extra credit opportunities are suggested. You may propose other ideas by sending them to me via email. This milestone is in two parts. You will develop your deliverables as detailed below. Then you will set up a time to meet with your TA. Your TA will look over your design and make recommendations. You will then turn in the corrected information. You will have only 1 week total to work on this milestone, so plan ahead to meet with the TA in time to incorporate their suggestions.

You will be designing the full application as described here. You will be required to implement a subset of the requirements as detailed in the coming feature slices. Any additional implementation beyond the listed feature slices are considered to be extra credit.

General
This application will allow students to share private recommendations and comments about movies they have seen, and to rank movies. Movie rankings can be reported overall, or by student major.

Projects in this class are open-ended and encourage you to be creative about possible solutions. Anything not specifically outlined in this description means you are free to design it however you like. For M11, we will be exchanging applications, and you will be able to see how user-friendly your choices really were.

Networks and Databases
Clearly an application like this would need commercial hosting and a database to become a commercial reality. Since these advanced courses are not pre-reqs for 2340, we are going to make a simple stand-alone web application that mimics the core features, but would not be commercially viable. Adding in a hosted web server (deployment to heroku for instance) and database (like postgres/mysql/mongo db) would be extra credit opportunities for the project.

Types of Users
User = this represents a regular student using the application. A person logged in as a user has no access to any Admin features.

Admin = the admin can unlock accounts, permanently ban users, and remove any inappropriate posts that might be stored on the system. While logged in as admin, you cannot access any normal user features (like posting movie reviews, etc).

This means that if a person wants to use the system normally and be an admin, they would need either two separate accounts, or some way to tell the system at login which user type they will be for the session. The way you enforce this is up to your design.

Login / Registration
To use the site, a student must be a registered user. To register, a student must enter their email, name, login/user name.

To login, the student enters their login/user name and their password. If correct, the student is allowed access to the system, otherwise they are re-prompted for their login information. If there are 3 incorrect login attempts, then the account is locked until an administrator unlocks it.

Logout
Since we are simulating a deployed web app on a single device, we need a way to change users. The application should allow the current user to log out so that a different user or admin can log in. Each user's information should be kept private from the others. Only an admin should be able to browse all the reviews and ratings.

Profile
Each user after registration may create and edit a profile. A user profile consists of the registration information plus their major (CS, EE, ME, ISYE, Math, Phys, Chem, ChemE) and a free text field for discussing their interests.

Movie Search
Many times, we want to rate or discuss a movie, but cannot remember the exact name. Rotten Tomatoes to the rescue. Using their REST api, you should allow a user to enter what they know of a movie title, then your application should display a list of matching movies. Finally, you should allow a user to select from the list the exact movie they are referring to.

Rating Movie
Users should be allowed to rate a movie. A rating consists of free-text comments, the user name rating the movie, their major, and some kind of rating. You may use the Rotten Tomatoe rating system, or a simple 5 star, or whatever you think best for your application.

Movie Recommendations
Users should be able to ask the system for recommendations. Recommendations can be across all users, or only ones with a particular major. The system should look for highest rated movies and display their names for the user to view.

New Movies
User should be able to ask for new releases. Using the API, request the new movies and display them in a list. A user should be able to then select one, and see if there are any ratings for that movie yet in the system.

Top Rentals
User should be able to ask for the top DVD rentals. Using the API, request the top rentals and display them in a list. A user should be able to select one, and see any ratings for that movie that are in the system. This list should be filtered for major if desired, so that only ratings for a given major are considered.

Admin Feature - Unlock / Ban Account.
If a user has posted inappropriate content, the admin may ban their account. If the account is banned, the user should not be able to login and they should be notified that their account is banned.

If a user had incorrectly logged in 3 times in a row, their account was locked, and they are unable to login. If a locked user tries to login, they should not be allowed to, and the system should respond with a notification that their account is locked. The admin should have a way to unlock a locked account.

Admin Feature - User list
An admin can request a listing of all registered users in the system. This should include their user name and status (active, banned, locked).

Extra Credit Opportunities:
Any additional features that you come up with are considered extra credit. See M13 for a listing of extra credit.
