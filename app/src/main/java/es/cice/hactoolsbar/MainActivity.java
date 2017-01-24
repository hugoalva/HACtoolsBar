package es.cice.hactoolsbar;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static final String TAG="MainActivity";
    private EditText buscaET;
    private ActionBar aBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar mitoolbar=(Toolbar)findViewById(R.id.includemitoolbar);
        // el uso de setSupportActionBar es coherente con
        // xml  =>>>>>> android.support.v7.widget.Toolbar
        // e
        // import =>>> import android.support.v7.app.ActionBar
        //si se usa setActionBar(mitoolbar);  fallaría

        setSupportActionBar(mitoolbar);
        aBar=getSupportActionBar();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=new MenuInflater(this);
        inflater.inflate(R.menu.toolbar_menu,menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        switch (item.getItemId()){
            case R.id.buscarItems:
                Log.d(TAG,"elegido buscar...");

                aBar.setDisplayShowCustomEnabled(true);
                aBar.setCustomView(R.layout.busca_layout);
                aBar.setDisplayShowTitleEnabled(false);

                buscaET=(EditText) aBar.getCustomView().findViewById(R.id.edtBusca);
                buscaET.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                        if(actionId== EditorInfo.IME_ACTION_SEARCH){
                            String textBusca=buscaET.getText().toString();
                            Log.d(TAG,"buscar: "+textBusca);

                            InputMethodManager imm=(InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                            //con parámetro 0 le indicamos...............
                            imm.hideSoftInputFromWindow(buscaET.getWindowToken(),0);

                            aBar.setDisplayShowCustomEnabled(false);
                            aBar.setDisplayShowTitleEnabled(true);

                            return true;
                        }
                        return false;
                    }

                });
                InputMethodManager imm=(InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(buscaET,InputMethodManager.SHOW_IMPLICIT);
                buscaET.requestFocus();

                break;
            case R.id.configuracionItems:
                Log.d(TAG,"elegido configuración...");
                break;
            case R.id.sobreItems:
                Log.d(TAG,"elegido About...");
                Intent miIntent=new Intent(this, Main2Activity2.class);
                startActivity(miIntent);

                break;
        }
        return super.onOptionsItemSelected(item);

    }

}
