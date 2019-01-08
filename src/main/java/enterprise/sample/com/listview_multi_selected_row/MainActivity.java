package enterprise.sample.com.listview_multi_selected_row;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private EditText filterText;
    private List<Student> studentList;    // Student from Student.class file
    String filename = "sd.txt";    // Save on samsung galaxy s7edge internal storage/sd.txt
    File file = new File(Environment.getExternalStorageDirectory(), filename);

    private Button btnSelection;
    private int STORAGE_PERMISSION_CODE = 1;
    static final String STATE_USER = "user";
    private String mUser;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      //  toolbar = (Toolbar) findViewById(R.id.toolbar);
        btnSelection = (Button) findViewById(R.id.btn_show);
        requestStoragePermission();


    /*    if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle("ListView Sample");
        }*/


        // Read in the root from the internal storage from sd.txt file in Samsung S7
        // And read txt file line by line with toast.
        studentList = new ArrayList<Student>();
/*        String data1 = "";
        try {
            FileInputStream fis = new FileInputStream(file);
            DataInputStream in = new DataInputStream(fis);
            BufferedReader br =
                    new BufferedReader(new InputStreamReader(in));
            String strLine;
            while ((strLine = br.readLine()) != null) {

                data1 = data1 + "\n" + strLine.toString();
                // myData = myData + strLine;
                String[] tokens = strLine.split(",");
                //Toast.makeText(getApplicationContext(), tokens[0], Toast.LENGTH_SHORT).show();
                Student st = new Student(tokens[0], tokens[1], tokens[2], Boolean.valueOf(tokens[3]));
                studentList.add(st);
            }
           // Toast.makeText(getApplicationContext(), data1, Toast.LENGTH_SHORT).show();
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }*/





        for (int i = 1; i <= 15; i++) {
            Student st = new Student("Student " + i, "androidstudent" + i
                    + "@gmail.com", "Message " + i, false);

            studentList.add(st);


/*                ///////////////////////  Read ArrayList in toast
            ArrayList<Student> al=new ArrayList<Student>();
            al.add(st);//adding Student class object
            Iterator itr=al.iterator();
            while(itr.hasNext()){
                    Student st1=(Student)itr.next();
              //  Toast.makeText(getApplicationContext(), st1.getName()+" "+st1.getRemarks()+" "+st1.getEmailId(), Toast.LENGTH_LONG).show();
                  }
                 ///////////////////////  Read ArrayList in toast*/

        }


        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // create an Object for Adapter
        mAdapter = new CardViewDataAdapter(studentList);

        // set the adapter object to the Recyclerview
        mRecyclerView.setAdapter(mAdapter);










        btnSelection.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                String data = "";
                List<Student> stList = ((CardViewDataAdapter) mAdapter).getStudentist();

                for (int i = 0; i < stList.size(); i++) {
                    Student singleStudent = stList.get(i);
                    if (singleStudent.isSelected() == true) {
                        data = data + "\n" + singleStudent.getName().toString() + " " + singleStudent.getEmailId().toString() + "\n" + singleStudent.getRemarks()+ "\n";
                    }

                }
              //  Toast.makeText(getApplicationContext(), , Toast.LENGTH_LONG).show();
                Toast.makeText(MainActivity.this,
                        "Selected Items: \n" + data, Toast.LENGTH_LONG)
                        .show();
            }
        });

/*        Button btn2 = (Button) findViewById(R.id.button);
        btn2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

            }

        });*/




    }

    public void SaveListView_To_Internal_Storage_File()
    {
        Toast.makeText(getApplicationContext(), "Saving Database", Toast.LENGTH_SHORT).show();
        // Save item.getValue().toString() to sd.txt   //////////////////////////////////////////////////
        String data = "";
        List<Student> stList = ((CardViewDataAdapter) mAdapter).getStudentist();
        try
        {
            FileWriter fw = new FileWriter(file,false); //the true will add the new data and false will clear the file and write to it again
            //  fw.write(Integer.toString(value)+" iiiiibb"+"\n");//appends the string to the file

            for (int i = 0; i < stList.size(); i++) {
                Student singleStudent = stList.get(i);

                data = data + "" + singleStudent.getName().toString() + "," + singleStudent.getEmailId().toString() + "," + singleStudent.getRemarks() + "," + singleStudent.isSelected();
                fw.write(data+"\n");//appends the string to the file
                data = "";
            }
            fw.close();
        }
        catch(IOException ioe)
        {
            System.err.println("IOException: " + ioe.getMessage());
        }// Save item.getValue().toString() to sd.txt   //////////////////////////////////////////////////

    }

    @Override        // By pressing the home button make toast message
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putString(STATE_USER, mUser);
        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
        Toast.makeText(this, "Database saving with home button", Toast.LENGTH_LONG).show();
        SaveListView_To_Internal_Storage_File();
    }



    @Override        // By pressing the back button make toast message
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        Toast.makeText(getApplicationContext(), "Database saving with back button", Toast.LENGTH_SHORT).show();
        SaveListView_To_Internal_Storage_File();

        return super.onKeyDown(keyCode, event);

    }



    private void requestStoragePermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)) {

            new AlertDialog.Builder(this)
                    .setTitle("Permission needed")
                    .setMessage("This permission is needed because of this and that")
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(MainActivity.this,
                                    new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
                        }
                    })
                    .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .create().show();

        } else {
            ActivityCompat.requestPermissions(this,
                    new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == STORAGE_PERMISSION_CODE)  {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission GRANTED", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Permission DENIED", Toast.LENGTH_SHORT).show();
            }
        }
    }


    


}

