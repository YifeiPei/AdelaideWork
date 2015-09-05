
/**
 * @filename array3_mod2.cpp
 * doing the samething using vector
 *author Yifei Pei
 **/

# include <iostream>
# include <string>
# include <vector>
using namespace std;

struct database_t {
  string name;
  int age;
  int score;
};

vector<database_t> database (15);


/**
 * This function ask for the data for the database
 * All the data needs to be typed in
 **/



void populateDatabase ()
{
  int m;
  cout << "Please enter the students' data. ";
  cout << "First type in name ,and then the age and score under the name, ";
  cout << "and then the nex name, age, and score. ";
  cout << "Please notice that there should not be any space ";
  cout << "between the first name and the last name.\n";
  for (m=0; m<15; m++)
    {
     cout << "Enter name: ";
     cin >> database[m].name;
     cout << "Enter age: ";
     cin >> database[m].age;
     cout << "Enter score: ";
     cin >> database[m].score;
     if (m!=14)
     cout << "The next student please.\n";
    }
  }

/**
 * This function print out the data when the user asks for the name
 * @param the name dimension in the database
 *
 * @return no value just end
 **/

void printDatabase (string name1)
{
  for (int x=0; x<15; x++)
    {
      if (database[x].name == name1)
        {
          cout << "Name: " << database[x].name << "\n";
          cout << "Age: " << database[x].age << "\n";
          cout << "Score: " << database[x].score << "\n";
        }
    }
}

/**
 * This function get the score when the user type in the name
 * @param the name dimension in the database
 *
 * @return return the score in the database
 **/

int getScore (string name1)
{
  for (int x=0; x<15; x++)
    {
      if (database[x].name == name1)
        return database[x].score;
    }
  return 0;
}


int main ()
{
  populateDatabase();
  cout << "Please enter the name who you want to know.\n";
  string name1;
  cin >> name1;
  cout << "The score of the person is " << getScore (name1) << ".\n";
  cout << "And the full information of this person is below.\n";
  printDatabase (name1);
  return 0;
}
