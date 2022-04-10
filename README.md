![logo](/docs/logo.png)

## SUMMARY

It ensures the flow of information between admin, employees and customers.

## DATABASE DIAGRAM

![diagram](/docs/diagram.png)

## RESTORE DATABASE FROM DUMP
```bash
psql -u postgres -f scripts/interprog_dump.sql
```

## ACTIVE ISSUES

- [ ] [The form components are not yet completed](https://github.com/csgn/interprog/issues/37)
- [ ] [The buttons are not worked after making an ajax request](https://github.com/csgn/interprog/issues/34)
- [ ] [Form inputs are not being checked after submitting](https://github.com/csgn/interprog/issues/46)
- [ ] [The job form is being submitted again after page is reloaded](https://github.com/csgn/interprog/issues/45)

## CONTRIBUTORS
- Metin Durmuş(mtndrms)
- Sergen Çepoğlu(csgn)
- Ramazan Kılıçaslan(rmznklcsln)
- Aykut Ecer(aecr12, aykut-wq)

## LICENSE

```
MIT License

Copyright (c) 2022 Sergen Çepoğlu, Metin Durmuş, Aykut Ecer, Ramazan Kılıçaslan

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
