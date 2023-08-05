#ifndef DEMOPORTAL_H
#define DEMOPORTAL_H
#include<bits/stdc++.h>
#include "../ecomm/Portal.h"
using namespace std;
class DemoPortal : public Portal
{
    private:
    string commandsort="";      //To stores sorting parameter
    string portalID;
    vector <string>splittedCommand;
    int pvsSize;                //To store the list size
    public:
    DemoPortal(){
        pvsSize=0;
        portalID="p038098535-1";
    }
    string getPortalID();
    void processUserCommand(string command);
    void checkResponse();
    
};
#endif
