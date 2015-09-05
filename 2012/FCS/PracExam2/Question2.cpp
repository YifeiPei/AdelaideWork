# include <iostream>
# include <string>
using namespace std;

/*
 * because now we do not know the numbers of the trees need to enter
 * so I use a variable to represent the numbers of the trees
 * when the user actually operate this database
 * they should define the amount they are going to enter
 */

int n;
#define size = n
int m;

/*
 * using the vector
 */



vector <database_t> tree_database (size);

/* using the folowing method to type in the database
 * this is the example of the method
 * this is not the real fuction to creat the database
 * this method is also suitable for the struct method not only vector
 */

for (m=0; m<size; m++)
  {
    cin >> tree_database [m].botanical_name;
    cin >> tree_database [m].common_name;
    cin >> tree_database [m].leaf_colour;
    cin >> tree_database [m].continent;
  }

/*
 * using the struct
 * also use the variables declared in the front
 */

struct database_t {
  string botanical_name;
  string common_name;
  string leaf_colour;
  string continent;
} tree_database [n];

/*
 * the database can also be made by array
 * because all the dimensions are string
 */

string array [n][4];

/*
 * the cin method is a little bit complicated
 */

for (n=0; n<15; n++)
  {
    for (m=0; m<4; m++)
      cin >> tree_database [n][m];
  }

/*
 * I can only generate the three different stuctures
 * the end
 */
