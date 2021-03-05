import sys
import io
from konlpy.tag import Mecab
from keras.preprocessing.text import Tokenizer
from keras.preprocessing.sequence import pad_sequences
from keras.models import load_model
import numpy as np
import socket


def main(argv):
    stopwords = ['도', '는', '다', '의', '가', '이', '은', '한', '에', '하', '고', '을', '를', '인', '듯', '과', '와', '네', '들', '듯', '지', '임', '게']

    if(socket.gethostname()[:7] == "DESKTOP"):
        loaded_model = load_model('./backend/files/sentiment_model.hdf5',compile=False)
        mecab = Mecab('./backend/files/mecab-ko-dic')
        X_train = open('./backend/files/save_xtrain.txt', 'r',encoding='UTF-8').readlines()
    else:
        loaded_model = load_model('/var/lib/jenkins/workspace/maven-test/backend/files/sentiment_model.hdf5',compile=False)
        mecab = Mecab('/var/lib/jenkins/workspace/maven-test/backend/files/mecab-ko-dic')
        X_train = open('/var/lib/jenkins/workspace/maven-test/backend/files/save_xtrain.txt', 'r',encoding='UTF-8').readlines()
        
    X_train = np.array([[num[1:-1] for num in item[1:-2].split(', ')] for item in X_train])
    tokenizer = Tokenizer(25078, oov_token = 'OOV') 
    tokenizer.fit_on_texts(X_train)
    new_sentence = mecab.morphs(argv[1]) # 토큰화
    new_sentence = [word for word in new_sentence if not word in stopwords] # 불용어 제거
    encoded = tokenizer.texts_to_sequences([new_sentence]) # 정수 인코딩
    pad_new = pad_sequences(encoded, maxlen = 80) # 패딩
    score = float(loaded_model.predict(pad_new)) # 예측
    if(score > 0.5):
        print("{:.2f}% 확률로 긍정 메세지입니다.".format(score * 100))
    else:
        print("{:.2f}% 확률로 부정 메세지입니다.".format((1 - score) * 100))

if __name__ == "__main__":
    sys.stdout = io.TextIOWrapper(sys.stdout.detach(), encoding = 'utf-8')
    sys.stderr = io.TextIOWrapper(sys.stderr.detach(), encoding = 'utf-8')
    main(sys.argv)