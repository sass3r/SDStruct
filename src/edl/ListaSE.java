/*
 * Copyright (C) 2013 Rafael Ramírez
 *
 *   SDStruct es software libre; puede redistribuirlo y/o modificarlo bajo 
 *   los términos de la Licencia Pública General GNU tal como se publica por 
 *   la Free Software Foundation; ya sea la versión 3 de la Licencia, o 
 *   (a su elección) cualquier versión posterior.
 *
 *   SDStruct se distribuye con la esperanza de que le sea útil, pero SIN 
 *   NINGUNA GARANTÍA; sin incluso la garantía implícita de MERCANTILIDAD o 
 *   IDONEIDAD PARA UN PROPÓSITO PARTICULAR. Vea la Licencia Pública 
 *   General GNU para más detalles.
 *
 *   Debería haber recibido una copia de la Licencia Pública General GNU
 *   junto con SDStruct; de lo contrario escriba a la Free Software Foundation, Inc.,
 *   51 Franklin Street, Fifth Floor, Boston, MA 02110-1301, EE. UU.
 *   También puede encontrar la licencia en la siguiente dirección 
 *   http://www.gnu.org/licenses/gpl.html 
 */

package edl;

/**
 * @author sass3r
 */

public class ListaSE<T> implements Lista<T>
{
    
    T ini;
    ListaSE<T> sig; 

    
    public ListaSE()
    {
        ini = null;        
        sig = null;
    }

    public boolean vacia()
    {
        return ini == null;
    }
    public void insertar(T d){
        if(!vacia())
            sig.insertar(d);
        else {   
            ini = d;
            sig = new ListaSE<T>();
        }
    }
    
    public int longitud(){
        int res=0;
        if(!vacia())
            res= 1 + sig.longitud();
        return res;     
    }
    
    public T acceder(int pos){
        T res = null;
        if(pos < longitud())
            res = acceder(0,pos);
        return res;
    }
    
    private T acceder(int p, int pos)
    {   
        T res = null;
        if(!vacia()){
            if(p == pos)
                res = ini;
            else
                res = sig.acceder(++p, pos);    
            }
        return res;
    }
    
    public void insertar(int pos, T d){
        if(pos <= longitud()){
            ListaSE<T> inicio = this;
            ListaSE<T> nuevo = new ListaSE<T>();
            insertar(0, pos, longitud(), inicio, nuevo,d);
        }
    }
    private void insertar(int p, int pos, int longitud, ListaSE<T> inicio, ListaSE<T> nuevo, T d){
        if(!vacia())
        {
            if(p==pos)
            {
                if(pos > 0)
                {
                    nuevo.sig = this;
                    nuevo.ini = d;
                    inicio.buscar(pos-1).sig = nuevo;
                }
                else
                {  
                    nuevo.sig = sig;
                    nuevo.ini = ini;
                    ini = d;
                    sig = nuevo;
                }
            }
            else
                sig.insertar(++p, pos, longitud, inicio, nuevo, d);
        }
        else
        {
            if(pos == longitud)  
            {
                ini = d;
                sig = new ListaSE<T>();
                    
            }
               
            
        }      
    }
   
    public ListaSE<T> buscar(int pos){
        ListaSE<T> res;
        if(pos < longitud())
            res= buscar(0,pos);
        else
            res=null;
        return res;
    }
    
    private ListaSE<T> buscar(int p, int pos){
        ListaSE<T> res = null;
        if(!vacia())
            if(p==pos)
                res = this;
            else
                res = sig.buscar(++p,pos);
        return res;    
        
        
    }

 
    public void mostrarLista() {
       if(!vacia()){ 
           System.out.print(" "+ ini + " ");
           sig.mostrarLista();
       }
           
    }
       
    public void primerosNElementos(int n) {
        if(n-1 == 0)
            System.out.print(" " + ini + " ");
        else { 
            System.out.print(" " + ini + " ");
            sig.primerosNElementos(n-1);
        }
    }
}

