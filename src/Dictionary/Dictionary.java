package Dictionary;

public class Dictionary {
    // Attributes
    DictNode head = null;
    DictNode tail = null;
    int size = 0;

    public Dictionary() {
    }

    private class DictNode {
        // Attributes
        String word;
        String definition;

        DictNode previous;
        DictNode next;

        public DictNode(String word, String definition, DictNode previous, DictNode next) {
            this.word = word;
            this.definition = definition;
            this.previous = previous;
            this.next = next;
        }

        public DictNode(String word, String definition) {
            this(word, definition, null, null);
        }

        public void printEntry() {
            System.out.println("#" + word + "\n" +
                    "\t" +  definition
            );
        }


        public String toString() {
            return "#" + word + "\n" + definition + "\n";
        }
    }

    /**
     * Method searches through dictionary list to find a word
     * @param word word to be located in dictionary list
     * @return null if empty dictionary or if word is not found
     * Otherwise, returns the word's node.
     */
    // todo replace null returns with thrown errors
    public DictNode find(String word) {
        // if dictionary is empty
        if (size==0)
            return null;

        // try finding word
        DictNode checkWord = head;

        while (checkWord!=null) {
            if (checkWord.word.equals(word))
                return checkWord;
            checkWord = checkWord.next;
        }

        // if word could not be found
        return null;
    }

    /**
     * Method searches through dictionary list to see if it contains a word
     * @param word word to be found in dictionary list
     * @return false if word is not found, otherwise true
     */
    public boolean contains(String word) {
        // if dictionary is empty
        if (size==0)
            return false;

        // try finding word
        DictNode checkWord = head;

        while (checkWord!=null) {
            if (checkWord.word.equals(word))
                return true;
            checkWord = checkWord.next;
        }

        // if word could not be found
        return false;
    }


    /**
     * Adds a dictionary entry in the appropriate (alphabetic) position.
     * @param word dictionary word to add
     * @param definition dictionary word's definition
     * @return false if word is already in the dictionary (does not update definition), true otherwise
     */
    public boolean addWord(String word, String definition) {
        // does word already exist?
        if (contains(word))
            return false;

        // create node
        DictNode newNode = new DictNode(word, definition);

        // if empty dictionary
        if (size == 0) {
            head = newNode;
            tail = newNode;

        // else add alphabetically
        } else {
            // add in head case
            if (word.compareTo(head.word) < 0) {
                newNode.next = head;
                head = newNode;
            }

            // add in tail case
            else if (word.compareTo(tail.word) >= 0) {
                tail.next = newNode;
                tail = newNode;
            }

            // insert where appropriate
            else {
                DictNode current = head;

                // find the appropriate position (aka somewhere in the middle case)
                while (current.next != null && word.compareTo(current.next.word) >= 0) {
                    current = current.next;
                }

                // insert the new node
                newNode.next = current.next;
                current.next = newNode;
            }
        }

        // update size
        size++;
        return true;
    }


    /**
     * Removes dictionary entry from dictionary, if it exists
     * @param word word to be removed
     * @return false if no word removed (did not exist), true otherwise
     */
    public boolean removeWord(String word) {
        DictNode removeNode = find(word);

        // return false if word is not in list
        if (removeNode==null)
            return false;

        // remove if in head
        if (removeNode.previous == null) {
            head = removeNode.next;
            head.previous = null;
        }

        // remove if in tail
        else if (removeNode.next == null){
            tail = removeNode.previous;
            tail.next = null;
        }

        // remove the node if in middle
        else{
            removeNode.next.previous = removeNode.previous;
            removeNode.previous.next = removeNode.next;
        }

        size--;
        return true;
    }

    // todo remove duplicates?

    // todo edit word definition
    public boolean editDefinition(String word, String newDefinition) {
        DictNode editNode = find(word);

        // if word does not exist
        if (editNode==null) {
            return false;
        }

        // if word exists, change definition
        editNode.definition = newDefinition;
        return true;
    }

    // todo display whole dictionary (forwards)
    public void display() {
        // if nothing to display
        if (size==0) {
            System.out.println("Dictionary is empty.");
            return;
        }

        // display
        DictNode displayNode = head;
        while (displayNode!=null) {
            displayNode.printEntry();
            displayNode = displayNode.next;
        }
    }

    // todo display dictionary entry
    public void display(String word) {
        if (size==0) {
            System.out.println("Dictionary is empty.");
        }
        DictNode displayNode = find(word);

        if (displayNode==null) {
            System.out.println(word + " could not be found in the dictionary.");
            return;
        }

        displayNode.printEntry();
    }

    // todo display words beginning with string
    public void search(String wordStart) {
        System.out.println("Dictionary is empty.");

        DictNode checkNode = head;
        boolean wasFound = false;

        while(checkNode!=null) {
            if(checkNode.word.startsWith(wordStart))
                checkNode.printEntry();
            checkNode = checkNode.next;
        }


        if (!wasFound) {
            System.out.println("Nothing beginning with " + wordStart + " could be found in the dictionary.");
        }
    }

    public String fileSaving() {
        DictNode outputNode = head;
        String ret = "";
        while (outputNode!=null) {
            ret += outputNode;
            outputNode = outputNode.next;
        }
        return ret;
    }

}
