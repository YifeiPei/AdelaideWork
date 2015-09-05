
/**
 * @filename array3_mod1.cpp
 * doing samething using struct
 *author Yifei Pei
 **/

# include <iostream>
# include <string>
using namespace std;

string name;
int m;
int l;

struct database_t {
  string name;
  int age;
  int score;
} database [15];

/**
 * This function ask for the data for the database
 * All the data needs to be typed in
 **/

void populateDatabase ()
{
  cout << "Please enter the students' data. ";
  cout << "First type in name ,and then the age and score under the name, ";
  cout << "and then the nex name, age, and score. ";
  cout << "Please notice that there should not be any space ";
  cout << "between the first name and the last name.\n";
  for (m=0; m<14; m++)
    {
     cout << "Enter name: ";  
     cin >> database[m].name;
     cout << "Enter age: ";
     cin >> database[m].age;
     cout << "Enter score: ";
     cin >> database[m].score;
     cout << "The next student please.\n";
    }
  cout << "Enter name: ";
  cin >> database [14].name;
  cout << "Enter age: ";
  cin >> database [14].age;
  cout << "Enter score: ";
  cin >> database [14].score;
}

/**
 * This function print out the data when the user asks the name
 * @param the name dimension in the database
 *
 * @return no value just end
 **/

int printDatabase (string name)
{
  if (database[l].name == name)
    {
      cout << "Name: " << database [l].name << "\n";
      cout << "Age: " << database [l].age << "\n";
      cout << "Score: " << database [l].score << "\n";
    }
  else
    {
      cout << "The name you typed in is not in the database. ";
      cout << "Thank you.\n";
    }
  return 0;
}

/**
 * This function get the score when the user type in the name
 * @param the name dimension in the database
 *
 * @return return the score in the database
 **/

int getScore (string name)
{
  if (database [l].name == name)
    {
      return database [l].score;
    }
  else
    {
      cout << "The name you typed in is not in the database. ";
      cout << "Thank you.\n";
    }
  return 0;
}


int main ()
{
  populateDatabase();
  cout << "Please enter the name who you want to know.\n";
  cin >> name;
  cout << "The score of the person is " << getScore (name) << ".\n";
  cout << "And the full information of this person is below.\n";
  printDatabase (name);
  return 0;
}
