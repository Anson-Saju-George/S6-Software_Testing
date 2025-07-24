import os

def list_files(start_path):
    for root, dirs, files in os.walk(start_path):
        level = root.replace(start_path, '').count(os.sep)
        indent = ' ' * 5 * level
        print(f"{indent}[DIR] {os.path.basename(root)}")
        sub_indent = ' ' * 5 * (level + 1)
        for f in files:
            print(f"{sub_indent}- {f}")

# Change this path to where you've cloned the repo
project_path = r"C:\Users\anson\Downloads\contact-manager"

list_files(project_path)
