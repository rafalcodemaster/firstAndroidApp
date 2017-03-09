package com.example.eryk.qubapp;

/**
 * QUBapp was designed for the assignemnt 2 for the MSc Software Development course, module: Web and Android development.
 * The purpose of the app is to allow the user to connect to the Queens University Webiste once correct login and password validated.
 * The login and password are set by default to "admin".
 *
 * @author Rafal Hejnicki
 * @version 1.0
 * imports of the android classes
 */

/**
 * imports of the android classes
 */

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.net.Uri;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    /**
     * var for correct password
     */
    String correctPassword = "admin";
    /**
     * var for correct login
     */
    String correctLogin = "admin";
    /**
     * var to set the attempts for login timeout
     */
    int attempts = 3;
    /**
     * string for the website address
     */
    String urlQUB = "http://www.qub.ac.uk/";
    /**
     * widget to display attempts left
     */
    TextView counter;
    /**
     * widgets to allow user input for the password and login
     */
    EditText pass, login;
    /**
     * initialise the button for confirm operation
     */
    Button confirm;
    /**
     * text view for the toast
     */
    TextView text;

    /**
     * main method to launch once the app is open from new or saved state
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(final Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        /*
         * method to hide the toolbar which is not in use for this particular app
         */
        getSupportActionBar().hide();
        /*
         * method to call the xml layout
         */
        setContentView(R.layout.activity_main);
        /*
         * initialising the confirm to its layout ID widget by calling the findViewById method and connecting it to the reasource method
         * R.id
         */
        confirm = (Button) findViewById(R.id.ConfirmButton);
        /**
         * initialising the counter to its layout ID widget by calling the findViewById method and connecting it to the reasource method
         * R.id
         */
        counter = (TextView) findViewById(R.id.Counter);
        /*
         * setting counter text to var with current attempts
         */
        counter.setText("Attempts Left " + attempts);
        /*
         * method to set the content for the confirm button by using the method setText on the top of the counter textView.
         */

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             /*
              call the method to invoke the toast set up.
              */
                toastSetUp();
              /*
              call the method to validate the user input
               */
                validateUserInput();
            }

            /**
             * method to set up the toast environment and connect it to the layout where it was declared
             */
            public void toastSetUp() {

                /*
                 * calling the toast layout by method getLayoutInflater();
                 */
                LayoutInflater inflater = getLayoutInflater();
                /*
                inflater.inflate method used to manipulate the predefined XML layout custom_widget
                 */
                View layout = inflater.inflate(R.layout.custom_widget, (ViewGroup)
                        /*
                        reference to the toast_layout_id by the use of findViewById from the R library
                         */
                        findViewById(R.id.toast_layout_id));
                /*
                 * connect text TextView to the View by findViewById() method
                 */
                text = (TextView) layout.findViewById(R.id.text);
                /*
                initiatiosation of the new Toast class
                 */
                Toast toast = new Toast(getApplicationContext());
                /*
                declaring the parameters of the toast location
                 */
                toast.setGravity(Gravity.BOTTOM, 0, 220);
                /*
                declaration of the lenght of the toast by the use of method setDueration(Toast.LNGHT_SHORT);
                 */
                toast.setDuration(Toast.LENGTH_SHORT);
                /*
                declaring the view of the toast by the method setView(layout):
                 */
                toast.setView(layout);
                /*
                declaration of the toast to be displayed by show() method
                 */
                toast.show();
            }

            /**
             * Method to establish connection of the app with the external webiste via combination of Intent class and method Uri.parse(), the url link is declared as the separate entity.
             */

            public void connectToWebstie() {
                /*
                 * Instantiationg new intent, Intent.ACTION_VIEW to parse url address from a string by using method Uri.parse();
                 * An object is used to tell a ContentProvider what one intends to access by
                 reference. The method Uri.parse creates a new Uri object from a ulrQUB string
                 String.
                 */
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(urlQUB));
                /*
                 * calling activity to display the website by the use of startActivity() method.
                 */
                startActivity(intent);
                /*
                 * extension of if statement sequence, if button confirm clicked and the happy path is not satisfied and there are more than 0 attempts availible
                 */
            }

            /**
             * method to validate the unser input from the editText widgets and parsing it into string. Then the method passes the data via the combination of if else statements.
             */
            public void validateUserInput() {
                /*
                 * initialising the password edit text to the edit text widget by calling the method findViewById.
                 */
                pass = (EditText) findViewById(R.id.password);
                /*
                 * initialising the login edit text to the edit text widget by calling the method findViewById.
                 */
                login = (EditText) findViewById(R.id.Login);
                /*
                 * formatting output from login and pass EditText widget to string by sourcing text by use of the getText() method and parsing it to string by toString().
                 */

                String a = pass.getText().toString();
                String b = login.getText().toString();
                /*
                 * if statement to validate the password, correct login and valid number of attempts by if statement incorporating 3 rules; a string to match correct password, b string to match correct login
                 * and number of attempts greater than 0.
                 */
                if ((a.equals(correctPassword)) && (b.equals(correctLogin) && attempts > 0)) {
                    /*
                     * set toast output by the use of setText() method.
                     */
                    text.setText("Login successful, Connecting ...");
                    /*
                    call of the method connectToWebsite() upon succesfull meeting of the good pass/login and positive attempts left.
                     */
                    connectToWebstie();

                } else if (attempts > 1) {
                    /*
                     * decrement the attempts by 1 to reduce availible attempts count.
                     */
                    --attempts;
                    /*
                     * update of the counter textView widget to display the current state of the attempts left
                     */
                    counter.setText("Attempts Left " + attempts);

                    text.setText("Attempts left: " + attempts + " ,Wrong login or password");
                    /*
                     * final if statement condition if there are no more attempts left.
                     */
                } else if (attempts > 0) {
                    /*
                     * method to disable the confirm button  setEnabled(false)
                     */
                    confirm.setEnabled(false);
                    /*
                     * update method to display 0 attempts availible
                     */
                    counter.setText("Attempts Left: " + (attempts - 1));
                    /**
                     * final message toast using setText() method
                     */
                    text.setText("Attempts left: " + (attempts - 1) + " ,Please Restart Application");
                }
            }

        });

    }
}
