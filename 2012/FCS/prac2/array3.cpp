
/**
 * @filename array3.cpp
 *
 *author Yifei Pei
 **/

# include <iostream>
# include <string>
using namespace std;

string database [15][3];
string name;
int n;
int m;
int l;

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
     for (n=0; n<3; n++)
     cin >> database [m][n];
     cout << "The next student please.\n";
    }
  for (n=0;n<3;n++)
    cin >> database [14][n];
}

/**
 * This function print out the data when the user asks for the name
 * @param the name dimension in the database
 *
 * @return no value just end
 **/

int printDatabase (string name)
{
  if (database [l][0] == name)
    {
      cout << "Name: " << database [l][0] << "\n";
      cout << "Age: " << database [l][1] << "\n";
      cout << "Score: " << database [l][2] << "\n";
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

string getScore (string name)
{
  if (database [l][0] == name)
    {
      return database [l][2];
    }
  else
    {
      cout << "The name you typed in is not in the database. ";
      cout << "Thank you.\n";
      return "Not Found";
    }
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
