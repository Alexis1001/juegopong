/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juego_pong;

import java.applet.AudioClip;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

/**
 *
 * @author PC
 */
public class FXMLDocumentController implements Initializable {
    
  @FXML private Button BotonIniciarJuego;
  @FXML private Circle Circulo;
  @FXML private Rectangle Rizquierda;
  @FXML private Rectangle Rderecha;
  @FXML private AnchorPane Escena2;
  Raquetas raquetas;
  KeyCode tecla;
  Pelota pelotita;
  @FXML private TextField Jugador1;
  @FXML private TextField jugador2;
   
  @FXML
  void IniciarJuego(ActionEvent event) {
       System.out.println("tamanio de escena2 "+Escena2.getBoundsInLocal());
       pelotita.MoverPelota();
    }
  
    @FXML
    void MoverRaquetas(KeyEvent event) {
       tecla = event.getCode();
       System.out.println("la tecla que presion es "+tecla);
       this.raquetas=new Raquetas(this.Rizquierda,this.Rderecha,this.tecla);
       this.raquetas.MoverRaquetas();
      
       }
  
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       this.pelotita=new Pelota(Circulo,Rizquierda,Rderecha,Jugador1,jugador2);
      }    
    
}

class Raquetas{
  Rectangle RaquetaI,RaquetaD;
  KeyCode tecla;
  Runnable correr;
  public Raquetas(Rectangle RaquetaI,Rectangle RaquetaD,KeyCode tecla){
     this.RaquetaI=RaquetaI;
     this.RaquetaD=RaquetaD;
     this.tecla=tecla;
  
     switch(tecla){
         case Z:
          correr=()->{   
           if(this.RaquetaI.getLayoutY()>=-11){
              this.RaquetaI.setLayoutY(this.RaquetaI.getLayoutY()-9);
              System.out.println("tamanio arriba "+this.RaquetaI.getLayoutY());
              }   
           };  
         break;
         case X:
         correr=()->{
           if(this.RaquetaI.getLayoutY()<=147){
              this.RaquetaI.setLayoutY(this.RaquetaI.getLayoutY()+9);
              System.out.println("tamanio abajo "+this.RaquetaI.getLayoutY());
              }  
         };    
         break;
         case UP:
         correr=()->{
           if(this.RaquetaD.getLayoutY()>=-11){
              this.RaquetaD.setLayoutY(this.RaquetaD.getLayoutY()-9);
              System.out.println("tamanio arriba "+this.RaquetaD.getLayoutY());
              }   
         };    
         break;    
         case DOWN:
         correr=()->{
           if(this.RaquetaD.getLayoutY()<=147){
              this.RaquetaD.setLayoutY(this.RaquetaD.getLayoutY()+9);
              System.out.println("tamanio abajo "+this.RaquetaD.getLayoutY());
              }   
         };    
         break;    
         default :
         correr=()->{
         System.out.println("Error"); 
         };

         
         }
     
     
   }  

 public void MoverRaquetas(){
     int hi=0;
    Thread hilo=new Thread(()->{
    
     do{
        Platform.runLater(correr);
         try {
         Thread.sleep(10);
         }catch(InterruptedException ex) {
          System.out.println("Error");
          }
            
      }while(hi!=0); 
    });
    hilo.setDaemon(true);
    hilo.start();
  }  
 
 
}

class Pelota{
   Circle circulo;
   Runnable ejecutar;
   int posicionx=1,posiciony=1;
   Rectangle RaquetaI,RaquetaD;
   TextField Jugador1;
   TextField Jugador2;
   int vida=0;
   int vida1=0;
 //  Audio audio = new Audio();
 /*  AudioClip rebote_1=audio.getAudio("/recursos/rebote_pelota1.wav");
   AudioClip rebote_2=audio.getAudio("/recursos/rebote_pelota2.wav");
   AudioClip falta=audio.getAudio("/recursos/falta.wav");
   */
   public Pelota(Circle circulo,Rectangle RaquetaI,Rectangle RaquetaD,TextField Jugador1,TextField jugador2){
   this.circulo=circulo;
   this.RaquetaI=RaquetaI;
   this.RaquetaD=RaquetaD;
   this.vida=0;
   this.vida1=0;
   this.Jugador1=Jugador1;
   this.Jugador2=jugador2;
   
ejecutar=()->{
    
    if(this.circulo.getLayoutX()>=502){
        posicionx=posicionx-1;
        System.out.println("entro en X max "+posicionx);
        System.out.println("posicion de la pelota "+this.circulo.getLayoutX());
        int suma=(int)(this.circulo.getLayoutX()+posicionx);
        System.out.println("la suma es "+suma);
        
        }
    if(this.circulo.getLayoutX()<=10){//10
         posicionx=posicionx*(-1);
         System.out.println("entro en X min "+posicionx);
         System.out.println("posicion de la pelota "+this.circulo.getLayoutX());
         int suma=(int)(this.circulo.getLayoutX()+posicionx);
         System.out.println("la suma es "+suma);
        }
    if(this.circulo.getLayoutY()>=272){
        posiciony=posiciony-1;
        System.out.println("entro en Y max "+posiciony);
        System.out.println("posicion de la pelota "+this.circulo.getLayoutY());
        int suma=(int)(this.circulo.getLayoutY()+posiciony);
        System.out.println("la suma es "+suma);
        
        }
    if(this.circulo.getLayoutY()<=15){
        posiciony=posiciony*(-1);
        System.out.println("entro en Y min "+posiciony);
        System.out.println("posicion de la pelota "+this.circulo.getLayoutY());
        int suma=(int)(this.circulo.getLayoutY()+posiciony);
        System.out.println("la suma es "+suma);
        }
    this.circulo.setLayoutX(this.circulo.getLayoutX()+posicionx);
    this.circulo.setLayoutY(this.circulo.getLayoutY()+posiciony);    
  }; 

} 
  public void MoverPelota(){

   Thread hilo=new Thread(()->{
       
     while(true){  
        Platform.runLater(ejecutar);
        colision_Pelota_RaquetaIzquierda();
        colision_Pelota_RaquetaDerecha();
     //   if(vida1==2||vida==2){
       //    break;
         //  }
      //Colision_Pared_Izquierda();
      //Colision_Pared_Derecha();
        try {
        Thread.sleep(10);
        }catch (InterruptedException ex) {
        Logger.getLogger(Pelota.class.getName()).log(Level.SEVERE, null, ex);
         }
      }   
   });
 
 hilo.setDaemon(true);
 hilo.start();
 
 }   
   
  public void colision_Pelota_RaquetaIzquierda(){
        
    Shape intersect = Shape.intersect(RaquetaI,circulo);{
           if(intersect.getBoundsInLocal().getWidth() != -1){ 
              posicionx=0;
              posicionx=posicionx+1;
              System.out.println("posicion "+posicionx);
              System.out.println("colision con raqueta 1");
              this.circulo.setLayoutX(this.circulo.getLayoutX()+posicionx);
             //audio.getAudio("rebote_pelota1.wav").play();
              }
      /*     if(this.circulo.getLayoutX()>=502){
             System.out.println("choco en la Pared"+this.circulo.getLayoutX());
             int pocicionx1=0;
             int pociciony1=0;
             pocicionx1=259;
             pociciony1=139;
             this.circulo.setLayoutX(pocicionx1);
             this.circulo.setLayoutY(pociciony1);
             System.out.println("pocicion en x "+ pocicionx1);
             System.out.println("pocicion en x "+ pociciony1);
             vida1++;
             Jugador2.setText(String.valueOf(vida1));
             }
           if(this.circulo.getLayoutX()<=10){
               System.out.println("coho en la Pared "+this.circulo.getLayoutX());
               int pocicionx=0;
               int pociciony=0;
               pocicionx=230;
               pociciony=120;
               this.circulo.setLayoutX(pocicionx);
               this.circulo.setLayoutY(pociciony);
               vida++;
               Jugador1.setText(String.valueOf(vida));
               }*/
                         
         }
  }
  
  public void colision_Pelota_RaquetaDerecha(){
       
    Shape intersect = Shape.intersect(RaquetaD,circulo);{
           if(intersect.getBoundsInLocal().getWidth() != -1){ 
              posicionx=0;
              posicionx=posicionx-1;
              System.out.println("posicion "+posicionx);
              System.out.println("colision con raqueta  derecha");
              this.circulo.setLayoutX(this.circulo.getLayoutX()+posicionx);
              //audio.getAudio("rebote_pelota2.wav").play();
              }
            
            
        }
  }

/*  public void Colision_Pared_Izquierda(){
   
      if(this.circulo.getLayoutX()<=10){
         System.out.println("coho en la Pared "+this.circulo.getLayoutX());
         int pocicionx=0;
         int pociciony=0;
         pocicionx=259;
         pociciony=139;
         this.circulo.setLayoutX(pocicionx);
         this.circulo.setLayoutY(pociciony);
         vida++;
         Jugador1.setText(String.valueOf(vida));
         }
 
     }
  
  public void Colision_Pared_Derecha(){
   
      if(this.circulo.getLayoutX()>=502){
         System.out.println("choco en la Pared"+this.circulo.getLayoutX());
         int pocicionx1=0;
         int pociciony1=0;
         pocicionx1=259;
         pociciony1=139;
         this.circulo.setLayoutX(pocicionx1);
         this.circulo.setLayoutY(pociciony1);
         System.out.println("pocicion en x "+ pocicionx1);
         System.out.println("pocicion en x "+ pociciony1);
         vida1++;
         Jugador2.setText(String.valueOf(vida1));
           }
 
    
   } 

 */ 

  }



/*class Audio {

    public AudioClip getAudio(String direccion) {
    return java.applet.Applet.newAudioClip(getClass().getResource(direccion));
    }
   
}*/