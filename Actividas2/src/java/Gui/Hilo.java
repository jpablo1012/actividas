/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

/**
 *
 * @author JPABLO
 */
public class Hilo extends Thread {

    @Override
    public void run() {
        while (true) {
            try {
                if (Main.dialog.enque) {
                    try {
                        Main.dialog.setPosicionLabel(Main.inicioSesion.frame.getLocation(), Main.inicioSesion.frame.getSize());
                    } catch (Exception e) {
                    }
                } else {
                    try {
                        Main.dialog.setPosicionLabel(Main.menu.frame.getLocation(), Main.menu.frame.getSize());
                    } catch (Exception e) {
                    }
                }
            } catch (Exception er) {
            }

            try {
                Thread.sleep(20);
            } catch (Exception e) {
            }
        }

    }
}
