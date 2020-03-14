package com.example.sshopappui;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;

import android.app.DownloadManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.bumptech.glide.util.Util;

import java.io.File;

public class menu extends AppCompatActivity {
    Button exit,aboutApp,contactMe,shareApp,download,sendEmail,downloadBut;
    Context context;
    AlertDialog.Builder builder;
    AlertDialog dialog;
    EditText email,subject,message;
    ImageView close,facebook,behance,limkedin,intagram;
    ImageButton back;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);



        exit = findViewById(R.id.exit);
        contactMe = findViewById(R.id.contactMe);
        aboutApp = findViewById(R.id.aboutApp);
        shareApp = findViewById(R.id.shareApp);
        download = findViewById(R.id.download);
        back = findViewById(R.id.back_b);
        facebook = findViewById(R.id.facebook);
        limkedin = findViewById(R.id.linkedin);
        behance = findViewById(R.id.behance);
        intagram = findViewById(R.id.instagram);
        downloadBut  = findViewById(R.id.download);

        downloadBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String URL = "https://github.com/lekhlifi51";
                Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse(URL));
                startActivity(intent);
            }
        });
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final AlertDialog.Builder builder = new AlertDialog.Builder(menu.this);
                builder.setTitle("Exit")
                        .setMessage("Are you sure you want to exit application")
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                                moveTaskToBack(true);
                            }
                        })
                        .show();

            }
        });

        contactMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                builder = new AlertDialog.Builder(menu.this);
                View view = getLayoutInflater().inflate(R.layout.sendemail_dialog,null,false);

                builder.setView(view);
                dialog = builder.create();
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;

                dialog.show();
                sendEmail = dialog.findViewById(R.id.sendEmail);
                email = dialog.findViewById(R.id.email);
                subject = dialog.findViewById(R.id.subject);
                message = dialog.findViewById(R.id.messagee);
                close = dialog.findViewById(R.id.close);

                sendEmail.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Intent.ACTION_SEND);

                        intent.putExtra(Intent.EXTRA_EMAIL,email.getText().toString());
                        intent.putExtra(Intent.EXTRA_SUBJECT,subject.getText().toString());
                        intent.putExtra(Intent.EXTRA_TEXT,message.getText().toString());

                        intent.setType("message/nfc288");
                        startActivity(Intent.createChooser(intent,"choose email client"));
                    }
                });

                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
            }
        });

        aboutApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder = new AlertDialog.Builder(menu.this);
                View view = getLayoutInflater().inflate(R.layout.about_app_dialog,null,false);

                builder.setView(view);
                dialog = builder.create();
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;

                dialog.show();

                close = dialog.findViewById(R.id.close3);

                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });



            }
        });

        shareApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShareCompat.IntentBuilder
                        .from(menu.this)
                        .setText("Sshop -Open source shop app UI [Professional UI design - Clean code ] .\n Includes animations, data saving(shared preferences)...\nDownload now the source code and enjoy customizing your own shop app\n   http://www.mediafire.com/file/1pgxn2i2ckn6n80/SshopAppUI.rar/file")
                        .setType("text/plain")
                        .setChooserTitle("Share app with")
                        .startChooser();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        // send social media link to webview activity for each one

        behance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        socialMedias(facebook,Uri.parse("https://www.facebook.com/profile.php?id=100004572885378&ref=bookmarks"));
        socialMedias(behance,Uri.parse("https://www.behance.net/mmd1"));
        socialMedias(limkedin,Uri.parse("https://www.linkedin.com/in/mohammed-lekhlifi-27255b131"));
        socialMedias(intagram,Uri.parse("https://www.instagram.com/meed_lekhlifi/"));

    }



    public void socialMedias(ImageView image, final Uri uri)
    {
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
    }


}
