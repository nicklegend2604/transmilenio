/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplo_mvc_tisc.controllers;

import ejemplo_mvc_tisc.models.Tisc;
import ejemplo_mvc_tisc.repository.ITiscRepository;
import ejemplo_mvc_tisc.repository.TiscBDImpl;
import ejemplo_mvc_tisc.views.TiscRegisterForm;
import ejemplo_mvc_tisc.factory.TiscFactory;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Gabriel Huertas <gabriel970826@gmail.com>
 */
public class Controller implements ActionListener {

    private TiscRegisterForm tiscRegisterForm;
    private final String CREATE_ACTION = "CREATE";
    private final String GENERATE_ACTION = "GENERATE";
    private final String GENERATE_PLAIN_TEXT_ACTION = "PLAIN_TEXT";
    private final String GENERATE_DB = "BUL_INSERT";
    private ITiscRepository repository;

    public Controller() {

        tiscRegisterForm = new TiscRegisterForm();
        repository = new TiscBDImpl();
        setUpActions();
    }

    private void setUpActions() {

        tiscRegisterForm.getCreateButton().addActionListener(this);
        tiscRegisterForm.getCreateButton().setActionCommand(CREATE_ACTION);
        
        tiscRegisterForm.getGenerateButton().addActionListener(this);
        tiscRegisterForm.getGenerateButton().setActionCommand(GENERATE_ACTION);

        tiscRegisterForm.getGeneratePlainText().addActionListener(this);
        tiscRegisterForm.getGeneratePlainText().setActionCommand(GENERATE_PLAIN_TEXT_ACTION);
        
        tiscRegisterForm.getGenerateDB().addActionListener(this);
        tiscRegisterForm.getGenerateDB().setActionCommand(GENERATE_DB);

    }

    private void createTisc() {

        String serial = tiscRegisterForm.getSerialField().getText();
        String type = tiscRegisterForm.getTypeField().getText();
        LocalDate expeditionDate = LocalDate.parse(tiscRegisterForm.getExpeditionDateField().getText(), DateTimeFormatter.ISO_DATE);
        Integer balance = Integer.parseInt(tiscRegisterForm.getBalanceField().getText());

        Tisc tisc = TiscFactory.createTisc(serial, type, expeditionDate, balance);
        repository.save(tisc);

    }

    private void displayTiscs(List<Tisc> tiscs) {

        StringBuilder builder = new StringBuilder();

        tiscs.forEach(tisc -> {
            builder.append(tisc);
        });

        tiscRegisterForm.getDisplayArea().setText(builder.toString());

    }

    private void fillForm() {
        Tisc tisc = TiscFactory.createRandomTisc();
        tiscRegisterForm.getSerialField().setText(tisc.getSerial());
        tiscRegisterForm.getTypeField().setText(tisc.getType());
        tiscRegisterForm.getExpeditionDateField().setText(tisc.getExpeditionDate().toString());
        tiscRegisterForm.getBalanceField().setText(tisc.getBalance().toString());
    }

    public void generatePlainText() {
        Integer ammount = Integer.parseInt(JOptionPane.showInputDialog("Cuantos registros quiere agregar"));

        BufferedWriter writer = null;
        try {
            StringBuilder builder = new StringBuilder();

            for (int i = 0; i < ammount; i++) {
                Tisc tisc = TiscFactory.createRandomTisc();
                builder.append(tisc.toString());
            }

            writer = new BufferedWriter(new FileWriter("prueba.txt"));
            writer.write(builder.toString());
            writer.close();

            JOptionPane.showMessageDialog(null, "Registros agregados!");

        } catch (IOException ex) {
            Logger.getLogger(TiscRegisterForm.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                writer.close();
            } catch (IOException ex) {
                Logger.getLogger(TiscRegisterForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void bulkInsert() {
        Integer ammount = Integer.parseInt(JOptionPane.showInputDialog("Cuantos registros quiere agregar"));
        for (int i = 0; i < ammount; i++) {
            Tisc tisc = TiscFactory.createRandomTisc();
            repository.save(tisc);
        }
        
        JOptionPane.showMessageDialog(null, "Registros agregados!");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        switch (action) {
            case CREATE_ACTION:
                createTisc();
                displayTiscs(repository.getAll());
                break;
            case GENERATE_ACTION:
                fillForm();
                break;
            case GENERATE_PLAIN_TEXT_ACTION:
                generatePlainText();
                break;
                
            case GENERATE_DB:
                bulkInsert();
                break;
        }
    }

}
