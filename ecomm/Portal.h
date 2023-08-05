#ifndef PORTAL_H
#define PORTAL_H
#include<bits/stdc++.h>
using namespace std;
class Portal 
{
    public:
        virtual void processUserCommand(string command); //Processes the User command
        virtual void checkResponse(); //Checks the Response
    
};
#endif
