/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package calculadora;

import java.util.ArrayList;
import java.util.Stack;

/**
 *
 * @author Sara
 */
public class Calcu extends javax.swing.JFrame {

    /**
     * Creates new form Calcu
     */

    private Stack<String> pilam;
    private Stack<String> pilaigual;
    
    public Calcu() {
        initComponents();
        this.setSize(450,500);
        this.setLocationRelativeTo(null);
        pilam = new Stack<String>();
        pilaigual = new Stack<String>();
       this.Area.setEditable(false);
    }

    public void mostrarPila(Stack<String> pila){
        Stack<String> pilax = new Stack<String>();
        String token;
        while(!pila.isEmpty()){
            token = pila.pop();
            pilax.push(token);
        }
       
        if(! pilax.isEmpty()){
            token = pilax.pop();
            this.pilaigual.push(token);
            Area.append(token);
            Area.append(" ");
        } 
               
    }
    
    public Double SacarOperando(Stack<String>pila){
        String n = "";
        String token = "";
        Stack<String> pilax = new Stack<String>();
        String aux="";
        
        while((!(aux.equals("+"))&&!(aux.equals("-"))&&!(aux.equals("*"))&&!(aux.equals("/")))&&(!pila.isEmpty())){
            token = pila.pop();
            pilax.push(token);
            if(!pila.isEmpty()){
                aux = pila.pop();
                pila.push(aux); 
            }
                     
        }
        
        Stack<String> clon = (Stack<String>) pilax.clone();
        while(!clon.isEmpty()){
            System.out.println(clon.pop());
        }
        Stack<String> reverse = new Stack<String>();
        while(!pilax.isEmpty()){
            reverse.push(pilax.pop());
        }
        while(!reverse.isEmpty()){
            n += reverse.pop();
        }
       
        return Double.parseDouble(n);
    }
    
    public Double Calcular(Stack<String> pila){
       String operador = "";
       Double token = null;
       while((pila.size()>1)&&(!pila.isEmpty())){
        Double op1 = this.SacarOperando(pila);
        operador = pila.pop();
        Double op2 = this.SacarOperando(pila);
        switch(operador){
            case "+":
                token = op1+op2;
                pila.push(token.toString());
                token = null;
                break;
           case "-":
                token = op1-op2;
                pila.push(token.toString());
                token = null;
                break;
        }
       }
       Double r = Double.parseDouble(pila.pop());
       return r;

    }
    public ArrayList<String> CalcularReales(ArrayList<String> lista){
        String c;
        int lder, lizq;
        int cont;
        while(lista.contains(".")){
            String izq = "", der = "";
            for(int i = 0; i < lista.size(); i++){
                c = lista.get(i);
                cont = i;
                if(c.equals(".")){
                    while(!c.equals("+")&&!c.equals("-")&&!c.equals("*")&&!c.equals("/")&&!(cont<=0)){
                        cont = cont-1;
                        c = lista.get(cont); 
                    }
                    if(c.equals("+")|| c.equals("-")|| c.equals("*")|| c.equals("/")){
                        cont = cont + 1;
                    }
                    while(!(c.equals("."))){
                            if(cont < 0) cont = 0;
                            izq+=lista.get(cont);
                            cont = cont + 1;
                            c = lista.get(cont);
                    }
                    cont = i + 1;
                    while(!c.equals("+")&&!c.equals("-")&&!c.equals("*")&&!c.equals("/")&&!(cont>=lista.size())){
                            der+=lista.get(cont);
                            cont = cont + 1;
                            if(cont < lista.size()){
                                c = lista.get(cont);
                            }
                    }
                    c = izq + "." + der;
                    lder = der.length();
                    lizq = izq.length();
                    ArrayList<String> novo = new ArrayList<>();
                            for(int j = 0; j < i-lizq; j++){
                                novo.add(lista.get(j));
                            }
                            novo.add(c);
                            for(int j = lder+i+1; j < lista.size(); j++){
                                novo.add(lista.get(j));
                            }
                            lista = novo;
                         break;   
                }
            }
        }
       return lista;
    }
    public Double CalcularLista(ArrayList<String> lista){
        String c;
        Double r = null;
        String op1, op2;
        lista = this.CalcularReales(lista);
        while(lista.size() > 1){
            if(lista.contains("*")|| (lista.contains("/"))){
                for(int i = 0; i < lista.size(); i++){
                    c = lista.get(i);
                    if(c.equals("*")){
                            op1 = lista.get(i - 1);
                            op2 = lista.get(i + 1);
                            r = Double.parseDouble(op1) * Double.parseDouble(op2);
                            ArrayList<String> novo = new ArrayList<>();
                            for(int j = 0; j < i-1; j++){
                                novo.add(lista.get(j));
                            }
                            novo.add(r.toString());
                            for(int j = i+2; j < lista.size(); j++){
                                novo.add(lista.get(j));
                            }
                            lista = novo; 
                            
                    }
                    if(c.equals("/")){
                            op1 = lista.get(i - 1);
                            op2 = lista.get(i + 1);
                            r = Double.parseDouble(op1) / Double.parseDouble(op2);
                            ArrayList<String> novo = new ArrayList<>();
                            for(int j = 0; j < i-1; j++){
                                novo.add(lista.get(j));
                            }
                            novo.add(r.toString());
                            for(int j = i+2; j < lista.size(); j++){
                                novo.add(lista.get(j));
                            }
                            lista = novo; 
                            
                    }
                }
            }else{
                Stack<String> pilax = new Stack<String>();
                for(int i = 0; i < lista.size(); i++){
                    pilax.add(lista.get(i));
                }
                Stack<String> reverse = new Stack<String>();
                while(!pilax.isEmpty()){
                    reverse.push(pilax.pop());
                }
                r = this.Calcular(reverse);
                ArrayList<String> l = new ArrayList<>();
                lista = l;
                
            }
        }
        
        
       Area.setText(r.toString());
       Area.append(" ");
    return r;
    }
          
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        Area = new javax.swing.JTextArea();
        CE = new javax.swing.JButton();
        siete = new javax.swing.JButton();
        ocho = new javax.swing.JButton();
        cuatro = new javax.swing.JButton();
        cinco = new javax.swing.JButton();
        seis = new javax.swing.JButton();
        uno = new javax.swing.JButton();
        tres = new javax.swing.JButton();
        menos = new javax.swing.JButton();
        igual = new javax.swing.JButton();
        mas = new javax.swing.JButton();
        division = new javax.swing.JButton();
        multiplicar = new javax.swing.JButton();
        nueve = new javax.swing.JButton();
        dos = new javax.swing.JButton();
        cero = new javax.swing.JButton();
        punto = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        Area.setColumns(20);
        Area.setRows(5);
        jScrollPane1.setViewportView(Area);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(0, 16, 430, 84);

        CE.setText("CE");
        CE.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CEMouseClicked(evt);
            }
        });
        CE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CEActionPerformed(evt);
            }
        });
        getContentPane().add(CE);
        CE.setBounds(160, 120, 75, 52);

        siete.setText("7");
        siete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sieteMouseClicked(evt);
            }
        });
        siete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sieteActionPerformed(evt);
            }
        });
        getContentPane().add(siete);
        siete.setBounds(25, 180, 67, 59);

        ocho.setText("8");
        ocho.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ochoMouseClicked(evt);
            }
        });
        getContentPane().add(ocho);
        ocho.setBounds(110, 180, 67, 59);

        cuatro.setText("4");
        cuatro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cuatroMouseClicked(evt);
            }
        });
        getContentPane().add(cuatro);
        cuatro.setBounds(25, 253, 67, 59);

        cinco.setText("5");
        cinco.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cincoMouseClicked(evt);
            }
        });
        getContentPane().add(cinco);
        cinco.setBounds(110, 253, 67, 59);

        seis.setText("6");
        seis.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                seisMouseClicked(evt);
            }
        });
        getContentPane().add(seis);
        seis.setBounds(192, 253, 67, 59);

        uno.setText("1");
        uno.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                unoMouseClicked(evt);
            }
        });
        uno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                unoActionPerformed(evt);
            }
        });
        getContentPane().add(uno);
        uno.setBounds(25, 324, 67, 59);

        tres.setText("3");
        tres.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tresMouseClicked(evt);
            }
        });
        getContentPane().add(tres);
        tres.setBounds(192, 324, 67, 59);

        menos.setText("-");
        menos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menosMouseClicked(evt);
            }
        });
        getContentPane().add(menos);
        menos.setBounds(350, 160, 67, 44);

        igual.setText("=");
        igual.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                igualMouseClicked(evt);
            }
        });
        getContentPane().add(igual);
        igual.setBounds(280, 270, 129, 110);

        mas.setText("+");
        mas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                masMouseClicked(evt);
            }
        });
        getContentPane().add(mas);
        mas.setBounds(270, 160, 67, 44);

        division.setText("/");
        division.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                divisionMouseClicked(evt);
            }
        });
        getContentPane().add(division);
        division.setBounds(350, 210, 67, 44);

        multiplicar.setText("*");
        multiplicar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                multiplicarMouseClicked(evt);
            }
        });
        getContentPane().add(multiplicar);
        multiplicar.setBounds(270, 210, 67, 44);

        nueve.setText("9");
        nueve.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nueveMouseClicked(evt);
            }
        });
        nueve.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nueveActionPerformed(evt);
            }
        });
        getContentPane().add(nueve);
        nueve.setBounds(190, 180, 67, 59);

        dos.setText("2");
        dos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dosMouseClicked(evt);
            }
        });
        getContentPane().add(dos);
        dos.setBounds(113, 324, 67, 59);

        cero.setText("0");
        cero.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ceroMouseClicked(evt);
            }
        });
        cero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ceroActionPerformed(evt);
            }
        });
        getContentPane().add(cero);
        cero.setBounds(110, 390, 67, 59);

        punto.setText(".");
        punto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                puntoMouseClicked(evt);
            }
        });
        punto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                puntoActionPerformed(evt);
            }
        });
        getContentPane().add(punto);
        punto.setBounds(70, 120, 75, 52);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CEActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CEActionPerformed

    private void sieteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sieteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sieteActionPerformed

    private void unoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_unoActionPerformed
   // TODO add your handling code here:
    }//GEN-LAST:event_unoActionPerformed

    private void unoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_unoMouseClicked
        pilam.push("1");
        this.mostrarPila(pilam);
        
    }//GEN-LAST:event_unoMouseClicked

    private void tresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tresMouseClicked
        pilam.push("3");
        this.mostrarPila(pilam);
    }//GEN-LAST:event_tresMouseClicked

    private void cuatroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cuatroMouseClicked
        pilam.push("4");
        this.mostrarPila(pilam);
    }//GEN-LAST:event_cuatroMouseClicked

    private void cincoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cincoMouseClicked
        pilam.push("5");
        this.mostrarPila(pilam);    

    }//GEN-LAST:event_cincoMouseClicked

    private void seisMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_seisMouseClicked
        pilam.push("6");
        this.mostrarPila(pilam);
    }//GEN-LAST:event_seisMouseClicked

    private void sieteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sieteMouseClicked
        pilam.push("7");
        this.mostrarPila(pilam);
    }//GEN-LAST:event_sieteMouseClicked

    private void ochoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ochoMouseClicked
        pilam.push("8");
        this.mostrarPila(pilam);
    }//GEN-LAST:event_ochoMouseClicked

    private void nueveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nueveMouseClicked
        pilam.push("9");
        this.mostrarPila(pilam);
    }//GEN-LAST:event_nueveMouseClicked

    private void nueveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nueveActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nueveActionPerformed

    private void dosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dosMouseClicked
        pilam.push("2");
        this.mostrarPila(pilam); 
    }//GEN-LAST:event_dosMouseClicked

    private void masMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_masMouseClicked
        pilam.push("+");
        this.mostrarPila(pilam);
    }//GEN-LAST:event_masMouseClicked

    private void menosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menosMouseClicked
        pilam.push("-");
        this.mostrarPila(pilam);
    }//GEN-LAST:event_menosMouseClicked

    private void multiplicarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_multiplicarMouseClicked
        pilam.push("*");
        this.mostrarPila(pilam);
    }//GEN-LAST:event_multiplicarMouseClicked

    private void divisionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_divisionMouseClicked
        pilam.push("/");
        this.mostrarPila(pilam);
    }//GEN-LAST:event_divisionMouseClicked

    private void igualMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_igualMouseClicked
        Stack<String> reverse = new Stack<String>(); // pilaigual contiene los elementos tal y como los introdujeses en una pila
        while(!this.pilaigual.isEmpty()){
            reverse.push(this.pilaigual.pop());
        }
        ArrayList<String> lista = new ArrayList<>();
        while(!reverse.isEmpty()){
            lista.add(reverse.pop());
        }
        
        
        Double r= this.CalcularLista(lista);
        System.out.println(r.toString());
        Stack<String> pr = new Stack<String>();
        pr.push(r.toString());
        this.pilaigual = pr;
              
        
    }//GEN-LAST:event_igualMouseClicked

    private void ceroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ceroMouseClicked
        pilam.push("0");
        this.mostrarPila(pilam);
    }//GEN-LAST:event_ceroMouseClicked

    private void ceroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ceroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ceroActionPerformed

    private void CEMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CEMouseClicked
        Area.setText(null);
        this.pilaigual.removeAllElements();
        
        Stack<String> clonb = (Stack<String>) this.pilam.clone();
        while(!clonb.isEmpty()){
           System.out.println(clonb.pop());
           System.out.println("clonb");
       }
        this.pilam.removeAllElements();
        
    }//GEN-LAST:event_CEMouseClicked

    private void puntoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_puntoMouseClicked
        pilam.push(".");
        this.mostrarPila(pilam);
    }//GEN-LAST:event_puntoMouseClicked

    private void puntoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_puntoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_puntoActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Calcu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Calcu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Calcu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Calcu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Calcu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea Area;
    private javax.swing.JButton CE;
    private javax.swing.JButton cero;
    private javax.swing.JButton cinco;
    private javax.swing.JButton cuatro;
    private javax.swing.JButton division;
    private javax.swing.JButton dos;
    private javax.swing.JButton igual;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton mas;
    private javax.swing.JButton menos;
    private javax.swing.JButton multiplicar;
    private javax.swing.JButton nueve;
    private javax.swing.JButton ocho;
    private javax.swing.JButton punto;
    private javax.swing.JButton seis;
    private javax.swing.JButton siete;
    private javax.swing.JButton tres;
    private javax.swing.JButton uno;
    // End of variables declaration//GEN-END:variables
}
