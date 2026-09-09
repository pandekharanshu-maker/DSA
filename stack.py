'''Implement the realtime for undo redo system for a text editing application using a stack data structure the system should support following operations
 1]make a change - a new change to document is made
 2]undo action - revert the most recent change and store it in potential redo
 3]redo action - reapply the most recent action
 4]display document - show the current state of the document after undoing or redoing the action.'''
class Text_Editor:
    def __init__(self):
        self.doc = ""
        self.UStack = []
        self.RStack = []
    def Text_Creator(self, new_doc):
        self.UStack.append(self.doc)
        self.doc = new_doc
        self.RStack.clear()
        print("Change made successfully.")
    def Undo(self):
        if not self.UStack:
            print("Nothing to undo.")
            return
        self.RStack.append(self.doc)
        self.doc = self.UStack.pop()
        print("Undo successful.")
    def Redo(self):
        if not self.RStack:
            print("Nothing to redo.")
            return
        self.UStack.append(self.doc)
        self.doc = self.RStack.pop()
        print("Redo successful.")
    def Display_Doc(self):
        print("Current Document =", self.doc)
t = Text_Editor()
while True:
    print("\n----- MENU -----")
    print("1. Make a Change")
    print("2. Undo Operation")
    print("3. Redo Operation")
    print("4. Display Document")
    print("5. Exit")
    try:
        choice = int(input("Enter your choice: "))
        if choice == 1:
            text = input("Enter new document text: ")
            t.Text_Creator(text)
        elif choice == 2:
            t.Undo()
        elif choice == 3:
            t.Redo()
        elif choice == 4:
            t.Display_Doc()
        elif choice == 5:
            print("Exiting...")
            break
        else:
            print("Invalid choice.")
    except ValueError:
        print("Please enter a valid number.")