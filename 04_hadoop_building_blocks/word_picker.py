import random


def generate_file(n_lines, file_name):
    """
    Given a wordlist, create a txt file with n_lines lines,
    each containing a random number of randomly sampled words.
    """
    with open('top_pwd_list.txt') as input_file, open(f'sample_input_files/{file_name}.txt', 'w') as output_file:
        word_list = [line.rstrip('\n') for line in input_file]
        for i in range(0, n_lines):
            line_words = random.sample(
                population=word_list,
                k=random.randrange(1,200)
            )
            output_file.write(' '.join(line_words) + '\n')

def generate_file_group(n_files):
    for i in range(0, n_files):
        generate_file(
            n_lines=random.randrange(200, 1000),
            file_name=f'file_{i}'
        )

generate_file_group(20);
