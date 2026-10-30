# RAVI OFFICE MASTER — क्लाउड APK बिल्ड पैकेज

यह पैकेज शुरुआती Android ऐप प्रोजेक्ट और GitHub Actions क्लाउड-बिल्ड workflow रखता है। कंप्यूटर/लैपटॉप की जरूरत नहीं; Android मोबाइल के Chrome से GitHub वेबसाइट पर ये चरण किए जा सकते हैं।

## मोबाइल से APK बनवाने के चरण
1. https://github.com पर अपना अकाउंट बनाएं या लॉगिन करें।
2. `New repository` चुनें। नाम `ravi-office-master` रखें और `Public` या `Private` चुनें।
3. `Add file` → `Upload files` चुनें। इस ZIP को पहले फोन में Extract करें, फिर इसके अंदर की सभी फाइलें/फ़ोल्डर (खासकर `.github` छिपा हुआ फ़ोल्डर भी) रिपॉजिटरी में अपलोड करें। अगर मोबाइल साइट hidden `.github` फ़ोल्डर अपलोड न करने दे, GitHub के `Add file` → `Create new file` से `.github/workflows/build-apk.yml` बनाकर workflow का कंटेंट डालें।
4. `Commit changes` दबाएं।
5. रिपॉजिटरी में `Actions` टैब खोलें। `Build RAVI OFFICE MASTER APK` workflow चुनें और `Run workflow` दबाएं (push पर भी build अपने-आप शुरू होगा)।
6. Build सफल होने पर उसी workflow run को खोलें। नीचे `Artifacts` में `RAVI-OFFICE-MASTER-debug-apk` डाउनलोड करें। ZIP को Extract करें।
7. `app-debug.apk` खोलकर इंस्टॉल करें। Android पूछे तो केवल अपने ब्राउज़र/फाइल मैनेजर के लिए “Install unknown apps” की अनुमति दें; यह ऐप खुद कोई विशेष परमिशन नहीं मांगता। इंस्टॉल के बाद उस अनुमति को बंद कर सकते हैं।

## सुरक्षा
- यह GitHub-hosted runner पर compile होता है। APK debug build है, Play Store release/signing build नहीं।
- GitHub अकाउंट/Actions उपयोग की उपलब्धता और limits GitHub की नीतियों पर निर्भर हैं।
- किसी को पासवर्ड, OTP या GitHub token न दें।

## वर्तमान ऐप की वास्तविक क्षमता
यह अभी शुरुआती ऑफलाइन ऐप है: हिंदी/अंग्रेजी टेक्स्ट लेखन, TXT सेव और सेव की गई TXT फाइलों की सूची। यह अभी पूर्ण Word/DOCX, Excel/XLSX, PowerPoint/PPTX या PDF editor नहीं है। उन इंजनों को अलग-अलग चरणों में जोड़ना होगा।
