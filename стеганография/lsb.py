import numpy as np
from PIL import Image

def Stego():
    print("Выберите что хотите сделать")
    print("1: Зашифровать")
    print("2: Расшифровать")

    func = input()

    if func == '1':
        print("Введите путь исходного изображения")
        src = input()
        print("Введите сообщение, которое необходимо скрыть")
        message = input()
        print("Введите название файла, которое бы хотели получить")
        dest = input()
        Encode(src, message, dest)

    elif func == '2':
        print("Введите путь к изображению с зашифрованным текстом")
        src = input()
        Decode(src)

    else:
        print("Ошибка: Вы ввели несуществующий вариант")

def Encode(src, message, dest): #АЛГОРИТМ ШИФРОВАНИЯ

    img = Image.open(src, 'r') # импорт изображения
    width, height = img.size # в переменные записывается размер изображения
    array = np.array(list(img.getdata())) # создаётся массив из изображения

    if img.mode == 'RGB': # является ли изображение RGB
        n = 3
    elif img.mode == 'RGBA': # является ли изображение RGBA
        n = 4
    total_pixels = array.size//n # в переменную записывается общее количество пикселей

    message += "@@@" # Добавление в сообщение кода для остановки кодирования 
    b_message = ''.join([format(ord(i), "08b") for i in message]) 
    req_pixels = len(b_message) # переменная, содержащая информацию о количестве необходимых символов

    if req_pixels > total_pixels: # если требуемых пикселей больше, чем пикселей в загруженном изображении
        print("Ошибка: необходим файл большего объёма")
    
    else:
        index=0
        for p in range(total_pixels): # цикл для записи сообщения в наименее значащие биты
            for q in range(0, 3):
                if index < req_pixels:
                    array[p][q] = int(bin(array[p][q])[2:9] + b_message[index], 2)
                    index += 1

    array=array.reshape(height, width, n)
    enc_img = Image.fromarray(array.astype('uint8'), img.mode)
    enc_img.save(dest)
    print("Изображение успешно зашифровано") 
    

def Decode(src): #АЛГОРИТМ ДЕШИФРОВАНИЯ

    print("начало алгоритма дешифрования")
    img = Image.open(src, 'r')
    array = np.array(list(img.getdata()))

    if img.mode == 'RGB':
        n = 3
    elif img.mode == 'RGBA':
        n = 4
    total_pixels = array.size//n

    hidden_bits = ""
    for p in range(total_pixels):
        for q in range(0, 3):
            hidden_bits += (bin(array[p][q])[2:][-1])

    print("точка 1")
    hidden_bits = [hidden_bits[i:i+8] for i in range(0, len(hidden_bits), 8)]
    print("точка 2")
    message = ""
    for i in range(len(hidden_bits)):
        if message[-3:] == "@@@":
            break
        else:
            message += chr(int(hidden_bits[i], 2))

        
    print("точка 3")
    
    if "@@@" in message:
        print("Скрытое сообщение:", message[:-3])
    else:
        print("Скрытое сообщение не найдено")

Stego()