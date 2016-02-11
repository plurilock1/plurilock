##Plurilock1 - CPSC 319 W2015

### Project

#### Behavioral Biometrics Authentication on Mobile Devices

#### Overview

The purpose of the application is to continuously record touch data from a touchscreen for the purpose of authenticating biometrically the user based on their usage pattern. This raw input is readily available through the phone’s API. The collected data is sent to the server for processing and authentication decision-making. The project will focus only on implementing the client application. 

### Motivation

This project is created for the Plurilock team in Victoria, BC by members of CPSC 319 Group 1 at the University of British Columbia. The purpose of the project is to retrieve detailed touch data on a client device and send it to the Plurilock server for user authentication. A mock banking app will be produced for the purposes of demonstrating this technology on the Android operating system.

### How to Get Started

#### Setting up the Repository Locally
    Please make sure git is installed and running on your computer prior to setting up this project. In addition, read the following getting started documentation for GitHub to properly set up your ssh key:

    - https://help.github.com/articles/set-up-git/ 

    Before being able to contribute to the Plurilock mock client project, you first have to add https://github.com/plurilock1/plurilock.git as a remote reference by running `git remote add origin https://github.com/plurilock1/plurilock.git`. After adding your remote reference, run `git clone git@github.com:plurilock1/plurilock.git` to clone the repository to your local machine.  

#### Pulling and pushing changes

    The GitHub repository has two main branches: production and staging. Staging is the environment where new changes will be pushed and any manually testing will be performed. When the main bugs are sorted out, you can push any changes in staging to production.

    We also want to keep a clean commit history, so when working in a local branch, please merge changes (git merge staging) to your local branch and then rebase (git rebase BRANCH_NAME) anything new onto staging before finally pushing your local changes to staging to the remote repository.

ex.

If your branch is called DEV, this is how you would check in your changes:

  `git checkout DEV
   git merge staging //resolve any merge conflicts that there might exist
   git push origin DEV
   git checkout staging
   git rebase DEV
   git push origin staging`

### Testing

TBD

### Contributors

* Matas @caliskimmer
* Ann @anneunjungkim
* James @yeah568
* Baek @baek4055
* Junoh @junohlee

### License

*A short snippet describing the license (MIT, Apache, etc.)*
