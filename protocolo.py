mapping = {
    'a': 'z',
    'c': 'x',
    'e': 'v',
    'g': 't',
    'i': 'r',
    'j': 'p',
    'm': 'n',
    'o': 'l',
    'q': 'j',
    's': 'h',
    'u': 'f',
    'w': 'd',
    'y': 'b',
}

def encrypt(message):
    encrypted_message = ""
    for char in message:
        if char in mapping:
            encrypted_message += mapping[char]
        else:
            encrypted_message += char  
    return encrypted_message

def decrypt(encrypted_message):
    decrypted_message = ""
    reverse_mapping = {value: key for key, value in mapping.items()} 
    for char in encrypted_message:
        if char in reverse_mapping:
            decrypted_message += reverse_mapping[char]
        else:
            decrypted_message += char  
    return decrypted_message
e="0"
while e == "0":
    sino= input("desea encriptar o desencriptar(E para encriptar, D para desencriptar)")
    if sino=="E":

        original_message = input("Por favor, ingrese el mensaje a encriptar: ")
        encrypted_message = encrypt(original_message)
        print("Mensaje encriptado:", encrypted_message)
    if sino=="D":
        decrypted_message = decrypt(encrypted_message)
        print("Mensaje desencriptado:", decrypted_message)
    e= input("Ingrese 1 si desea salir del programa, 0  si desea continuar ")