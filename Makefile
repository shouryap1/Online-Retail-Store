CC=g++

test: PortalMain.o DemoPortal.o Portal.o
	$(CC) PortalMain.o DemoPortal.o Portal.o -o test

PortalMain.o: PortalMain.cpp
	$(CC) -c PortalMain.cpp

DemoPortal.o : Demo\DemoPortal.cpp Demo\DemoPortal.h
	$(CC) -c Demo\DemoPortal.cpp

Portal.o : ecomm\Portal.cpp ecomm\Portal.h
	$(CC) -c ecomm\Portal.cpp

clean:
	rm *.o test