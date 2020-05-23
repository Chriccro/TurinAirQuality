public class Temperature{
 
 public String Anno;
 public float Inverno;
 public float Primavera;
 public float Estate;
 public float Autunno;
 

 public Temperature(String anno, float inverno, float primavera, float estate, float autunno) {
  Anno = anno;
  Inverno = inverno;
  Primavera = primavera;
  Estate = estate;
  Autunno = autunno;
 }



 public String getAnno() {
  return Anno;
 }

 public float getInverno() {
  return Inverno;
 }

 public float getPrimavera() {
  return Primavera;
 }

 public float getEstate() {
  return Estate;
 }

 public float getAututnno() {
  return Autunno;
 }

 public void setAnno(String anno){
     Anno = anno;
 }
    
 public void setInverno(int inverno){
     Inverno = inverno;
 }
    
 public void setPrimavera(int primavera){
     Primavera = primavera;
 }
    
 public void setEstate(int estate){
     Estate = estate;
 }
    
 public void setAutunno(int autunno){
     Autunno = autunno;
 }   

 public String toString() {

  String str = "";
  char grado = 176;
  
  str += "Anno: " + Anno + "\n";
  str += "Temperatura media in inverno: " + Inverno + grado+"C\n";
  str += "Temperatura media in primavera: " + Primavera + grado+"C\n";
  str += "Temperatura media in estate: " + Estate + grado+"C\n";
  str += "Temperatura media in autunno: " + Autunno + grado+"C\n\n";

  return str;
 }
 
}
