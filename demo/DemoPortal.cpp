#include "DemoPortal.h"

string DemoPortal::getPortalID(){
  return portalID;
}

void DemoPortal::processUserCommand(string command)
{
  ofstream MyFile("PortalToPlatform.txt",ios::app);
  // Write to the file
  if(command==""){
    return;
  }
  vector<string> arr(0);
  string temp=command;
  int i=0;
      string s; 
          while (i < temp.length()) {
        if (temp[i] != ' ') {
            s += temp[i];       // Append the char to the temp string.
        }
        else{
            arr.push_back(s);
            s.clear();
        }
        i++;
    }
  arr.push_back(s);        //Adding string to vector arr.
  this->splittedCommand=arr;
  if(arr.size()==5 && arr[2]!="Buy"){
    this->commandsort=arr[4];         //Basically this checks whether we have any parameter for sorting or not.
  }                                   //If we have a parameter then if-loop condtions work otherwise else-loop works
  else{
    this->commandsort="";
  }
  MyFile <<command<<"\n";            //Writes in file
  MyFile.close();                    // Closes the file
}

void DemoPortal::checkResponse()
{
  if(this->commandsort==""){
    string myText;
    vector<string>list_response;
    ifstream MyReadFile("PlatformToPortal.txt");       //If there is no sorting parameter then this if-loop works.
    while (getline (MyReadFile, myText))               //All the relevant data gets printed in terminal as it is
    {                                                  // as no sorting has to be done.
      list_response.push_back(myText); //pushes the response to the vector
    }
      for(int j=0;j<list_response.size();j++){
      vector<string> arr(0);
      string temp = list_response[j];
      // cout<<temp<<"this is temp"<<endl;
      int i=0;
      string s; 
          while (i < temp.length()) {
        if (temp[i] != ' ') {
            // Append the char to the temp string.
            s += temp[i]; 
        }
         else {
            // cout << s << endl;
            arr.push_back(s);
            s.clear();
        }
        i++;
    }
    arr.push_back(s);
    if(arr[0]==this->getPortalID() && this->splittedCommand[1] == arr[1])
    {
      cout<<list_response[j]<<endl;
    }
    }
    MyReadFile.close();  
  }
  else{
    multimap<float,string>sortPrice;                      //We have used Multimap for sorting on the basis of parameter.
    multimap<string,string>sortName;
    vector<string>lists;
    ifstream MyReadFile("PlatformToPortal.txt"); 
    string myText="";
    while (getline (MyReadFile, myText)) 
    {
      lists.push_back(myText);                      //This lists has all the relevant command in it.
    }
    MyReadFile.close();

    for(int j=0;j<lists.size();j++){
      vector<string> arr(0);
      string temp = lists[j];
      int i=0;
      string s; 
      while (i < temp.length()) {       //this loop helps in splitting the command on a space and store it in arr.
        if (temp[i] != ' ') {
            s += temp[i]; 
        }
         else {
            arr.push_back(s);
            s.clear();
        }
        i++;
    }
    arr.push_back(s);

    if(arr.size()==6 && arr[0]==this->getPortalID() && this->splittedCommand[1] == arr[1]){                  //this works when a sorting parameter has been given
    float var = stoi(arr[4]);
    sortPrice.insert(pair<float,string>(var,temp));
    sortName.insert(pair<string,string>(arr[2],temp));
    }

    if(arr.size()==3 && arr[0]==this->getPortalID() && this->splittedCommand[1] == arr[1]){                  //this works when response of "Buy" command comes.
        cout<<myText<<endl;
      }
    }

    if(commandsort=="Price"){         //This works when sorting parameter is Price. Helps in printing the sorted list.
          for(auto i:sortPrice){
            cout<<i.second<<endl;
          }
        }
    else if(commandsort=="Name"){     //This works when sorting parameter is Name. Helps in printing the sorted list.
      for(auto i:sortName){
        cout<<i.second<<endl;
      }
    }
    pvsSize=lists.size();             //helps in storing the lists size.
    sortPrice.clear();
  }
}
