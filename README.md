[![Build Status](https://travis-ci.org/plurilock1/plurilock.svg?branch=master)](https://travis-ci.org/plurilock1/plurilock)

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

  ```
   git checkout DEV
   git merge staging //resolve any merge conflicts that there might exist
   git push origin DEV
   git checkout staging
   git rebase DEV
   git push origin staging
   ```

### Testing

#### Unit Testing
 
Local Unit Testing tutorial link: http://developer.android.com/training/testing/unit-testing/local-unit-tests.html

Unit testing for this project is done using JUnit 4. All Unit Tests should be placed in the proper folder in the test package which mirrors the structure of the project. When writing a test, begin with writing a simple Java class and import any necessary libraries. Each class name should describe what the tests are actually testing (aka no vague names!). In JUnit 4, there is no special syntax for each method other than every method should be public and return void AND each test needs to be prefixed by the *@test* annotation. 

A simple example (shown below) is located on the production branch and contains one test case on for the User class:

```
package group1.cpsc319.plurilock_client.Model;

import org.junit.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

/**
 * Created by Matas on 2/21/16.
 */
public class UserTest {

    @Test
    public void user_printsString() {
        User test = new User("Matas", "matas@alumni.ubc.ca");

        assertEquals("assert userMessage prints properly", test.userMessage(), "Hello, Matas! Is your email, matas@alumni.ubc.ca still valid?");
    }
}
```

Each test may use different types of assertions or other testing methods. View the JUnit Documentation to see what methods can be used.

##### Running a Unit Test

With the current directory set to the directory containing feature tests, run the following command: TBD

#### Assertion Testing

Calabash assertion testing tutorial: https://developer.xamarin.com/guides/testcloud/calabash/creating-calabash-tests/
Calabash predefined steps: https://github.com/calabash/calabash-android/blob/master/ruby-gem/lib/calabash-android/canned_steps.md
Gherkin Syntax: https://github.com/cucumber/cucumber/wiki/Gherkin

Assertion tests are created using the Calabash testing framework using Ruby. Calabash tests are located within the *features* folder at $HOME/plurilock-client/ and are written in a similar manner. An example is shown below:

```
Feature: Credit card validation.
  Credit card numbers must be exactly 16 digits.

  Scenario: Credit card number is too short
    Given I use the native keyboard to enter "123456" into text field number 1
    And I touch the "Validate" button
    Then I see the text "Credit card number is too short."
```

Each feature test is contained in its own file and may have multiple scenarios. As can be seen above, the syntax is very similar to standard English. Please read the Gherkin link above to learn more about the syntax structure used.

Although Calabash contains some predefined steps to use for testing (see Calabash predefined steps link above), it also supports custom steps (detailed under the section in the Calabash tutorial link titled *Creating a step definition*

##### Running an Assertion Test

With the current directory set to the directory containing feature tests, run the following command: TBD

### Contributors

* Matas @caliskimmer
* Ann @anneunjungkim
* James @yeah568
* Baek @baek4055
* Junoh @junohlee
* Johnny @jh1993

### License

None at this time
