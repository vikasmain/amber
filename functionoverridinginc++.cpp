//if derived class and base class have same functions with same name then after creating the objects of derived class derived class function will callled but not base class.to remove this we use scope resolution operato(::
#include<iostream>
using namespace std;
class vikas
{
public:
void getdata(){
	cout<<"hello from base:";
}	
};
class vikaskaladka:public vikas{
	public:
		void getdata()
		{
vikas::getdata();
			cout<<"hello from derived:";
		}
};
int main()
{
vikaskaladka v1;
v1.getdata();	
}
