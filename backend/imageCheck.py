import cv2, os, sys
import numpy as np
import time
from keras.preprocessing.image import img_to_array
from keras.models import load_model
import base64
import socket

def main(argv):
    # 경로 정해주기
    if(socket.gethostname()[:7] == "DESKTOP"):
        r = open('./frontend/public/textFiles/' + argv[1], mode='rt', encoding='utf-8')
    else:
        r = open('/var/lib/jenkins/workspace/maven-test/frontend/public/textFiles/' + argv[1], mode='rt', encoding='utf-8')
    
    raw = ""
    for line in r:
        raw += line
    r.close()

    # path = "C:/image/test.png"

    # color = cv2.imread(img, cv2.IMREAD_UNCHANGED)

    path = raw[22:]
    
    decoded_data = base64.b64decode(path)
    np_data = np.fromstring(decoded_data,np.uint8)
    color = cv2.imdecode(np_data,cv2.IMREAD_UNCHANGED)

    # 경로 확인
    if(socket.gethostname()[:7] == "DESKTOP"):
        face_detection = cv2.CascadeClassifier('./backend/files/haarcascade_frontalface_default.xml')
        emotion_classifier = load_model('./backend/files/emotion_model.hdf5', compile=False)
    else:
        face_detection = cv2.CascadeClassifier('/var/lib/jenkins/workspace/maven-test/backend/files/haarcascade_frontalface_default.xml')
        emotion_classifier = load_model('/var/lib/jenkins/workspace/maven-test/backend/files/emotion_model.hdf5', compile=False)
    
    EMOTIONS = ["Angry" ,"Disgusting","Fearful", "Happy", "Sad", "Surpring", "Neutral"]

    # Convert color to gray scale
    gray = cv2.cvtColor(color, cv2.COLOR_BGR2GRAY)

    faces = face_detection.detectMultiScale(gray,
                                                    scaleFactor=1.1,
                                                    minNeighbors=5,
                                                    minSize=(30,30))


    # Create empty image
    canvas = np.zeros((250, 300, 3), dtype="uint8")

    # For the largest image
    face = sorted(faces, reverse=True, key=lambda x: (x[2] - x[0]) * (x[3] - x[1]))[0]
    (fX, fY, fW, fH) = face
    # Resize the image to 48x48 for neural network
    roi = gray[fY:fY + fH, fX:fX + fW]
    roi = cv2.resize(roi, (48, 48))
    roi = roi.astype("float") / 255.0
    roi = img_to_array(roi)
    roi = np.expand_dims(roi, axis=0)

    # Emotion predict
    preds = emotion_classifier.predict(roi)[0]
    emotion_probability = np.max(preds)
    label = EMOTIONS[preds.argmax()]

    # print(str(round(emotion_probability * 100)))
    print(str(format(round(preds[3] * 100),".0f")))
    print(label)

    now = int(round(time.time() * 1000))
    filename = str(now) + '.png'

    # if(label == "Happy"):
    #     cv2.imwrite("C:/image/" + filename, color)
    #     print(filename)
    #     print("true")
    # cv2.imwrite("C:/image/" + filename, frame)

    # 경로 확인
    if(socket.gethostname()[:7] == "DESKTOP"):
        cv2.imwrite("./frontend/public/images/" + filename, color)
    else:
        cv2.imwrite("/var/lib/jenkins/workspace/Gitlab/frontend/public/images/" + filename, color)
    
    print(filename)

    # cv2.imshow('Sample', color)

    # cv2.waitKey(0)

if __name__ == "__main__":
    main(sys.argv)