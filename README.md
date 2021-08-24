# yolov4-tflite-CUSTOM-android-app
* Real-Time CUSTOM detection android app with yolov4 and tensorflow_lite  
* Referenced from [here](https://github.com/hunglc007/tensorflow-yolov4-tflite)

# KOREAN GUIDELINES
## Introduction
> * Object Detection을 단순히 분류하자면, 모든 것을 한 번에 처리하는 yolo와 파이프라인을 거쳐서 동작하는 faster-RCNN 등이 있을 것입니다. 때문에 Yolo는 비교적 빠른 속도로 인식이 가능한 대신, 선형적인 모양이나 국부적인 패턴 등을 인식하는 데 한계가 있습니다. 반면 RCNN의 경우 매우 세부적인 형태까지 detecting하는 것이 가능합니다.
> * 이 레포지토리는 tensorflow를 이용하여 darknet을 통해 스스로 학습한 yolo weights로 yolo-v4를 사용해 detecting하는 안드로이드 애플리케이션을 만드는 과정을 다룹니다. 각자 다양한 데이터를 학습시켜 필요에 맞는 애플리케이션을 제작하는데 큰 도움이 되리라 생각합니다.
### 이 레포지토리를 빌드하여 다음을 얻을 수 있음
* 자신이 원하는 카테고리의 object detection용 yolo weight
* 해당 yolo weight와 tensorflow로 작동하는 yolov4 알고리즘 코드
* 해당 yolo weight을 사용하여 실시간 사물인식을 시행하는 안드로이드 앱
* 내장되어있는 assets에 다음과 같은 모델들이 현 레포지토리에 올라와있음:
  * 오바마와 오바마가 아닌 '사람들'을 구분하는 모델 (Obama vs Person)
  * 전반적인 일상생활의 물건들을 인식하는 yolo 기본 모델 (coco) 

### Requirements
> 이 프로젝트를 수행하기 위해서는 다음의 조건이 필요하거나, 권장됩니다.
* [Android Studio](https://developer.android.com/studio)
* [Google Colab](https://www.google.com/search?q=google+colab&oq=google+colab&aqs=chrome.0.69i59l2j0i131i433i512j69i60l3j69i65l2.1294j0j7&sourceid=chrome&ie=UTF-8)
* [Roboflow](https://app.roboflow.com/)

## How To Use (A_to_Z)
### 1. YOLO 데이터셋 생성
* 인식시키고자 하는 이미지 데이터를 준비합니다. (최소 10장 이상을 권장)
* [Roboflow](https://app.roboflow.com/)에 접속하여, 새 프로젝트를 생성합니다. 
* 이미지를 업로드하고, 가이드라인에 따라 원하는 영역에 blob박스를 표시한 뒤 레이블을 설정합니다. 여러 이미지를 업로드 하여 다양한 레이블을 넣어도 좋습니다.
* 데이터를 생성하고, export합니다. 이때, *Format : YOLO Darknet*, *show download code* 으로 export하여 받은 코드를 저장해둡니다.

> * 2~4는 Mobile_Object_Detection.ipynb에서 진행됩니다. GPU 사용이 있으므로 Google Colab을 사용하는 것을 권장합니다. 학습 이미지 데이터에 따라 장시간 소요될 수 있습니다.
> * 이때 Colab에서 그날 할당 받은 GPU 카드가 Tesla K80일 경우, 이 과정이 원활하지 않을 수 있습니다. Tesla T4 등급 이상의 GPU 카드를 할당받았을 때 진행하기를 권장합니다. (이 때문에 무료 Colab을 사용한다면 어떤 날은 해당 프로젝트를 진행하지 못할 수 있음.)
### 2. Yolov4 tiny darknet 학습
* 레포지토리 복사
``` git
git clone https://github.com/skykongkong8/yolov4-tflite-CUSTOM-android-app.git
```
* Configure CUDA on Colab for YOLOv4
* Install Darknet for YOLOv4 on Colab
* Configure from custom Darknet
* Write custom training config for YOLOv4
* Train custom YOLOv4 Detector
* Infer custom objects with saved yolov4 wegiths
### 3. Darknet 프레임워크 모델을 tensorflow lite모델로 전환
* Install tensorflow
* *중요!* convert 하기 이전에, `!pip install tensorflow==2.3.0rc0`을 통해 tensorflow버전을 바꿔주어야 합니다. 그렇지 않으면 mutable-object를 처리할 수 없다는 에러가 나옵니다.
* Convert
### 4. tensorflow lite 모델 저장
* 저장 후, 자신에 구글 드라이브에 다운로드된 .tflite 파일과, _darknet.labels 파일을 복제해둡니다.
* 오류가 발생하면, 처음부터 다시 한 번 실행해 보세요.

### 5. demo android 앱 생성
* android 디렉토리를 Android Studio로 엽니다. (이때, 앱을 사용할 Android SDK Level과 프로젝트에서 빌드할 Level이 서로 호환되는지 반드시 확인하세요.)
* app > src > main > assets 에 아까 복제해두었던 .tflite 파일과 ._darknet.lavels 파일을 저장합니다.
* app > src > main > java > DetectorActivity와 같은 경로의 CameraActivity 에서, 사용할 yolo weights와 label을 다음과 같이 수정하여 지정합니다.
 ```java
 TF_OD_API_MODEL_FILE = "your-yolov4-tiny-weight.tflite";
 TF_OD_API_LABELS_FILE = "file:///android_asset/_darknet.labels";
 ```
* app > src > main > java > tflite > YoloV4Classifier 에서 다음과 같이 수정합니다.
```java
isTiny = true;
```
* 앱을 빌드하고, 핸드폰과 연결하여 RUN 합니다.
