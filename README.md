# Check Members

Using the github API, it goes through all the public and private github members in a github organization and, if they don't have a name filled in on their github profile, it email them saying so with a link to github.com where they can enter their name.

## Getting Started

You will need some github organization with public and private members to test the application.

Read these sites:
https://developer.github.com/v3/#parameters
https://developer.github.com/v3/orgs/members/#get-organization-membership
https://developer.github.com/v3/users/

### Prerequisites

Netbeans or other Java IDE.

### Installing

Install additional libraries:
https://code.google.com/archive/p/json-simple/downloads    
https://mvnrepository.com/artifact/javax.mail/mail/1.4.7
Read this site:
https://netbeans.org/kb/73/java/project-setup.html?print=yes#projects-classpath

## Running the tests

No tests were developed to the project

## Deployment

The app is done in three steps:
1)	Github API call for the organization members
2)	Github API call for every member information
3)	Checking the members with no name set up on github and sending them emails

## Contributing

N/A

## Authors

* **Yura Vasiuk** - https://github.com/YuriiVasiuk/check-members

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

## Acknowledgments

* To BYU IT department for the interesting Skill Evaluation
 

