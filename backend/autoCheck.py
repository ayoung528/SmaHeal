import cv2, os, sys
import numpy as np
import time
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

    path = raw[22:]
    
    decoded_data = base64.b64decode(path)
    np_data = np.fromstring(decoded_data,np.uint8)
    color = cv2.imdecode(np_data,cv2.IMREAD_UNCHANGED)

    now = int(round(time.time() * 1000))
    filename = str(now) + '.png'

    # 경로 확인
    if(socket.gethostname()[:7] == "DESKTOP"):
        cv2.imwrite("./frontend/public/images/" + filename, color)
    else:
        cv2.imwrite("/var/lib/jenkins/workspace/Gitlab/frontend/public/images/" + filename, color)
    
    print(filename)

if __name__ == "__main__":
    main(sys.argv)