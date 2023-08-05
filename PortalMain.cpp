#include <bits/stdc++.h>
#include "demo/DemoPortal.h"
using namespace std;
int main()
{
    DemoPortal *pt = new DemoPortal();  //Creates and object of DemoPortal
    cout<<pt->getPortalID()<<endl;      //Created a string variable for storing user input.
    string command;                     // this helps in concatinating.
    string dummy_space=" ";             // helps in tracking the request ID
    int counter=1;
    while(true)
    {
        getline(cin,command);   //input from user
        if(command!=""){

        string final_command="";         //This is the final command which has portal ID and requestID also in it.

        if(command!="Check"){
        final_command=pt->getPortalID()+dummy_space+"r"+to_string(counter)+dummy_space+command;     //Made the final command
        counter++;     //RequestID's number gets increased by 1.
        }
        pt->processUserCommand(final_command);//Sends to processUserCommand the final command.
        pt->checkResponse();       //checkResponse called.
        }
}
    
}
