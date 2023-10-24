mapping = {
    'a': 'hola ',
    'b': 'si ',
    'c': 'no ',
    'd': 'estas ',
    'e': 'bien ',
    'f': 'y ',
    'g': 'tu ',
    'h': 'gracias ',
    'i': 'adios ',
    'j': 'hasta ',
    'k': 'buenos dias ',
    'l': 'lo siento ',
    'm': 'puedo ',
    'n': 'hacerte ',
    'ñ': 'comer ',
    'o': 'voy ',
    'p': 'viajar ',
    'q': 'bienvenido ',
    'r': 'igualmente ',
    's': 'la ',
    't': 'show ',
    'u': 'otro  ',
    'v': 'estas ',
    'w': 'wow ',
    'x': 'que ',
    'y': 'de ',
    'z': 'tu ',

    
    
    'A': 'comimos ',
    'B': 'el ',
    'C': 'ella ',
    'D': 'nosotros ',
    'E': 'hacemos ',
    'F': 'hacer ',
    'G': 'telefono ',
    'H': 'va ',
    'I': 'como ',
    'J': 'pronto ',
    'K': 'come ',
    'L': 'a ',
    'M': 'trabajo ',
    'N': 'huele ',
    'Ñ': 'le ',
    'O': 'estaba ',
    'P': 'buscando ',
    'Q': 'su ',
    'R': 'poco ',
    'S': 'tiempo ',
    'T': 'amigo ',
    'U': 'ven ',
    'V': 'te ',
    'W': 'invito ',
    'X': 'una ',
    'Y': 'copa ',
    'Z': 'tomo ',

    '1':'hombre ',
    '2':'mujer ',
    '3':'señor ',
    '4':'señora ',
    '5':'niño ',
    '6':'niña ',
    '7':'chavo ',
    '8':'chava ',
    '9':'boca ',
    '0':'cabeza ',

    '.':'pie ',
    ',':'mano ',
    '(':'uña ',
    ')':'pegue ',
    '=':'pegar ',
    '¿':'esta',
    '?':'rico ',
    '¡':'rica ',
    '!':'estas ',
    '&':'amiga ',
    '$':'conocido ',
    '#':'conocida ',
    '+':'colega ',
    '*':'olvido ',
    '<':'ser ',
    '>':'perro ',
    '-':'entre semana ',
    '_':'fin de semana ',
    ',':'ayer ',
    ';':'hoy ',
    '/':'año ',  


    
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