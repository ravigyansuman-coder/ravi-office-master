package com.raviofficemaster.app;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;

/** Offline-first starter shell. Files are saved only in this app's private storage. */
public class MainActivity extends Activity {
    private final int navy = Color.rgb(16, 27, 50);
    private final int gold = Color.rgb(214, 168, 79);
    private LinearLayout root;

    @Override public void onCreate(Bundle state) {
        super.onCreate(state);
        showHome();
    }

    private TextView text(String value, int size, int color, boolean bold) {
        TextView t = new TextView(this);
        t.setText(value); t.setTextSize(size); t.setTextColor(color);
        if (bold) t.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        t.setPadding(8, 8, 8, 8);
        return t;
    }

    private void base(String title) {
        root = new LinearLayout(this); root.setOrientation(LinearLayout.VERTICAL);
        root.setBackgroundColor(Color.rgb(246, 247, 250));
        TextView bar = text(title, 21, Color.WHITE, true);
        bar.setBackgroundColor(navy); bar.setPadding(20, 22, 16, 22);
        root.addView(bar); setContentView(root);
    }

    private void showHome() {
        base("RAVI OFFICE MASTER");
        ScrollView scroll = new ScrollView(this);
        LinearLayout body = new LinearLayout(this); body.setPadding(16, 18, 16, 24);
        body.setOrientation(LinearLayout.VERTICAL);
        body.addView(text("आपका ऑफलाइन ऑफिस", 23, navy, true));
        body.addView(text("Hindi + English  •  Offline-first  •  Version 0.1.0", 13, Color.DKGRAY, false));
        String[][] modules = {
            {"WORD MASTER", "पत्र, आवेदन और टेक्स्ट दस्तावेज", "word"},
            {"PDF MASTER", "PDF टूल्स — अगला विकास चरण", "pdf"},
            {"EXCEL MASTER", "टेबल और गणना — अगला विकास चरण", "excel"},
            {"POWERPOINT MASTER", "प्रेजेंटेशन — अगला विकास चरण", "ppt"},
            {"OFFICE WRITER", "सरकारी पत्र एवं कार्यालय प्रारूप", "writer"},
            {"OCR MASTER", "फोटो से टेक्स्ट — अगला विकास चरण", "ocr"},
            {"FILE MANAGER", "ऐप के निजी दस्तावेज", "files"}
        };
        for (String[] m : modules) {
            Button b = new Button(this); b.setAllCaps(false);
            b.setText(m[0] + "\n" + m[1]); b.setTextSize(15);
            b.setTextColor(navy); b.setBackgroundTintList(android.content.res.ColorStateList.valueOf(Color.WHITE));
            LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(-1, -2); p.setMargins(0, 12, 0, 0);
            body.addView(b, p);
            b.setOnClickListener(v -> openModule(m[2]));
        }
        body.addView(text("नोट: यह शुरुआती निर्माण संस्करण है। पूर्ण DOCX/PDF/XLSX संपादन इंजन अभी जोड़े जाने बाकी हैं।", 12, Color.DKGRAY, false));
        scroll.addView(body); root.addView(scroll);
    }

    private void openModule(String module) {
        if (module.equals("word") || module.equals("writer")) { showEditor(); return; }
        if (module.equals("files")) { showFiles(); return; }
        base(module.toUpperCase());
        root.addView(text("यह मॉड्यूल अगले विकास चरण में जोड़ा जाएगा।", 18, navy, true));
        Button back = new Button(this); back.setText("मुख्य पृष्ठ पर लौटें"); back.setOnClickListener(v -> showHome()); root.addView(back);
    }

    private void showEditor() {
        base("WORD MASTER — सरल संपादक");
        EditText name = new EditText(this); name.setSingleLine(true); name.setHint("फाइल का नाम (जैसे patra.txt)"); root.addView(name);
        EditText content = new EditText(this); content.setGravity(Gravity.TOP); content.setMinLines(12);
        content.setHint("यहाँ हिंदी या अंग्रेजी में लिखें…");
        root.addView(content, new LinearLayout.LayoutParams(-1, 0, 1));
        Button save = new Button(this); save.setText("इस मोबाइल में सेव करें"); root.addView(save);
        save.setOnClickListener(v -> {
            String filename = name.getText().toString().trim();
            if (filename.isEmpty()) filename = "document.txt";
            filename = filename.replaceAll("[^a-zA-Z0-9._-]", "_");
            if (!filename.endsWith(".txt")) filename += ".txt";
            try (FileOutputStream out = openFileOutput(filename, MODE_PRIVATE)) {
                out.write(content.getText().toString().getBytes(StandardCharsets.UTF_8));
                Toast.makeText(this, "सेव हुआ: " + filename, Toast.LENGTH_LONG).show();
            } catch (Exception e) { Toast.makeText(this, "सेव नहीं हुआ: " + e.getMessage(), Toast.LENGTH_LONG).show(); }
        });
        Button back = new Button(this); back.setText("मुख्य पृष्ठ"); back.setOnClickListener(v -> showHome()); root.addView(back);
    }

    private void showFiles() {
        base("FILE MANAGER");
        String[] files = fileList();
        if (files.length == 0) root.addView(text("अभी कोई फाइल सेव नहीं है।", 16, navy, false));
        for (String f : files) root.addView(text("• " + f, 16, navy, false));
        Button back = new Button(this); back.setText("मुख्य पृष्ठ"); back.setOnClickListener(v -> showHome()); root.addView(back);
    }
}
